package com.movie.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.model.Bookings;
import com.movie.projection.BookingResponse;
import com.movie.request.BookingRequest;
import com.movie.service.BookingsService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/booking")
@Slf4j
public class BookingController {
	
	private final BookingsService bookingsService;

	public BookingController(BookingsService bookingsService) {
		this.bookingsService = bookingsService;
	}

	@PostMapping()
	@ApiOperation(value = "Make a new booking for the selected movie and other given parameters", notes = "Make a new booking for the selected movie and other given parameters")
	public String createBooking(BookingRequest request) {
		return bookingsService.createBooking(request);
	}
	
	@GetMapping("/booking/history/{mobileno}")
	@ApiOperation(value = "Retrieve the list of user booking history", notes = "Retrieve the list of user booking history")
	public List<BookingResponse> getBookingsByMobileNo(@PathVariable(name = "mobileno") String mobileNo) {
		return bookingsService.getBookingsByMobileNo(mobileNo);
	}
}
