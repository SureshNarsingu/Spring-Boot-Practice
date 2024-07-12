package com.movie.projection;

import java.time.LocalDate;

public interface OnboardMovieResponse {

	public LocalDate getonboardMovieDate();
	public Movie getMovie();
	public Theatre getTheatre();

	interface Movie {
		public String gettitle();	
		public int getId();
		public String getGenre();

	}

	interface Theatre {		
		public int getId();
		public String getName();
		public String getLocation();

	}
}
