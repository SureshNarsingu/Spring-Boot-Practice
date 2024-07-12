package com.movie.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.movie.model.Bookings;
import com.movie.model.Movie;
import com.movie.model.Theatre;
import com.movie.projection.BookingResponse;
import com.movie.repository.BookingsRepository;
import com.movie.repository.MovieRepository;
import com.movie.repository.TheatreRepository;
import com.movie.request.BookingRequest;
import com.movie.service.BookingsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingsServiceImpl implements BookingsService {

	private final MovieRepository movieRepository;

	private final BookingsRepository bookingsRepository;

	@Autowired
	private TheatreRepository theatreRepository;

	// field injection of the ModelMapper
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	private ObjectMapper objectMapper;

	// constructor injection of the MovieRepository
	public BookingsServiceImpl(MovieRepository movieRepository, BookingsRepository bookingsRepository) {
		this.bookingsRepository = bookingsRepository;
		this.movieRepository = movieRepository;
	}

	@Override
	public String createBooking(BookingRequest request) {
		try {
			Optional<Theatre> optionalTheatre = theatreRepository.findById(request.getTheatreId());
			if (!optionalTheatre.isPresent()) {
				log.error("Theatre not found by id: {}", request.getTheatreId());
				return "booking not successful";
			}
			Bookings bookings = modelMapper.map(request, Bookings.class);
			bookings.setReservedSeat(request.getReservedSeat().toString());
			Map<String, String> readValue = objectMapper.readValue(optionalTheatre.get().getSeatPriceRange(),
					Map.class);
			int totalAmount = calculateTotalAmount(request.getReservedSeat(), readValue);
			bookings.setTotalPayment(totalAmount);
			bookingsRepository.save(bookings);
			return "Tickets booked succesfully";
		} catch (Exception e) {
			log.error("Error occured while booking tickets request: {}, error: {}", new Gson().toJson(request),
					e.getMessage());
			return null;
		}

	}

	@Override
	public List<Bookings> getBookings() {
		log.info("Received request for getting the list of user booking history");
		List<Movie> movies = movieRepository.findAll();
		List<Bookings> bookingHistory = new ArrayList<>();
		for (Movie movie : movies) {
			int bookedTickets = 0;// movie.getTotalSeats() - movie.getAvailableSeats();
			if (bookedTickets > 0) {
				int totalPrice = 0;// okedTickets * movie.getPrice();
				Bookings booking = modelMapper.map(movie, Bookings.class);
				// map the additional fields in the BookingHistory
				// booking.setBookedTickets(bookedTickets);
				// booking.setTotalPrice(totalPrice);
				bookingHistory.add(booking);
			}
		}
		return bookingHistory;
	}

	@Override
	public List<BookingResponse> getBookingsByMobileNo(String mobileNo) {
		List<BookingResponse> response = bookingsRepository.findByMobileNo(mobileNo);
		return response;
	}

	public int calculateTotalAmount(List<Integer> selectedSeats, Map<String, String> priceRangeMap) {
		int totalAmount = 0;
		for (Integer seat : selectedSeats) {
			String range = getRangeForSeat(priceRangeMap, seat);
			if(range!=null) {
			totalAmount += Integer.parseInt(range);
			}
		}
		return totalAmount;
	}

	private String getRangeForSeat(Map<String, String> priceRangeMap, int seat) {
//		if (seat >= 1 && seat <= 30)
//			return "1-30";
//		else if (seat >= 31 && seat <= 60)
//			return "31-60";
//		else if (seat >= 61 && seat <= 90)
//			return "61-90";
		for (String seatRangeKey : priceRangeMap.keySet()) {
			String[] rangeBounds = seatRangeKey.split("-");
			int minSeat = Integer.parseInt(rangeBounds[0]);
			int maxSeat = Integer.parseInt(rangeBounds[1]);
			if (seat >= minSeat && seat <= maxSeat) {
				return priceRangeMap.get(seatRangeKey);
			}

		}

		throw new IllegalArgumentException("Seat number out of range.");
	}
}
