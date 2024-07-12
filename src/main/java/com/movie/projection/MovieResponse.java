package com.movie.projection;

import java.time.LocalDate;

public interface MovieResponse {

	public Integer getId();
	public String getTitle();
	public String getCastCrew();
	public String getDescription();
	public String getGenre();
	public LocalDate getReleaseDate();
}
