package com.movie.service;

import java.util.List;

import com.movie.model.Bookings;
import com.movie.projection.BookingResponse;
import com.movie.request.BookingRequest;

public interface BookingsService {

	public String createBooking(BookingRequest request);

	List<Bookings> getBookings();

	public List<BookingResponse> getBookingsByMobileNo(String mobileNo);
}
