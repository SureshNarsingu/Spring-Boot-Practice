package com.movie.request;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {

	private String movieName;
	private Integer theatreId;
	private String location;
	private List<Integer> reservedSeat;
	private int totalPayment;
	private LocalDate bookingDate;
	private String showTimings;
	private String mobileNo;
}
