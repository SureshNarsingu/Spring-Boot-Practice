package com.movie.request;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheaterRequest {
	private String name;
	private String location;
	private int totalSeats;
	private Map<String, String> seatPriceRange;
	private Map<String, String> showTimings;

}
