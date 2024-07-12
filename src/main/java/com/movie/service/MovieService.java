/**
 * 
 */
package com.movie.service;

import java.time.LocalDate;
import java.util.List;

import com.movie.model.Bookings;
import com.movie.model.Movie;
import com.movie.projection.MovieResponse;
import com.movie.request.NewMovieRequest;

/**
 * 
 */
public interface MovieService {

	void updateMovie(Integer id, NewMovieRequest request);

	void deleteMovie(Integer id);

	public String addMovie(NewMovieRequest request);

	Movie getMovieById(Integer id);

	List<MovieResponse> getAllMovies(String title, String location, String genre);
	
	List<MovieResponse> getAllMovies();

	/**
	 * This methods filters the movies based on the given parameters
	 * 
	 * @param title represents the title of the movie
	 * @param location represents the location of the movie
	 * @param genre represents the genre of the movie
	 * @return returns the list of movies that match the given parameters
	 */
	List<Movie> filterMovies(String title, String location, String genre);
}
