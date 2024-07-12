package com.movie.projection;

import java.time.LocalDate;

public interface BookingResponse {

	public Integer getId();
	public String getMovieName();
	public TheaterInfo getTheatre();
	public String getLocation();
	public String getReservedSeat();
	public Integer getTotalPayment();
	public LocalDate getBookingDate();
	public String getShowTimings();
	public String getMobileNo();
	
	interface TheaterInfo {
		public String getSeatPriceRange();
	}
}
