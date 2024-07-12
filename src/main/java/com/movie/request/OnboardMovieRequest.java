package com.movie.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnboardMovieRequest {

	private LocalDate date;
	
	private Integer theatreId;
	
	private Integer movieId;
	
	private String showTimings;  
}
