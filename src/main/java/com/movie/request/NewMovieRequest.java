/**
 * 
 */
package com.movie.request;

import java.time.LocalDate;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
public class NewMovieRequest {

	private String title;
	private Map<String,String> castCrew;
	private String description;
	private String genre;
	private LocalDate releaseDate;
}
