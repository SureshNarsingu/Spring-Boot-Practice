package com.movie.service.impl;

import java.util.List;
import java.util.function.Predicate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.movie.model.Movie;
import com.movie.projection.MovieResponse;
import com.movie.repository.MovieRepository;
import com.movie.repository.OnboardMovieRepository;
import com.movie.request.NewMovieRequest;
import com.movie.service.MovieService;

import lombok.extern.slf4j.Slf4j;

/**
 * This service class is responsible for the business logic related to the Movie entity
 */
@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

	private final MovieRepository movieRepository;
	
	private final OnboardMovieRepository onboardMovieRepository;

	// field injection of the ModelMapper
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private ObjectMapper objectMapper;

	// constructor injection of the MovieRepository
	public MovieServiceImpl(MovieRepository movieRepository, OnboardMovieRepository onboardMovieRepository) {
		this.movieRepository = movieRepository;
		this.onboardMovieRepository = onboardMovieRepository;
	}

	/**
	 * This methods filters the movies based on the given parameters
	 * 
	 * @param title represents the title of the movie
	 * @param date represents the date of the movie
	 * @param location represents the location of the movie
	 * @param genre represents the genre of the movie
	 * @return returns the list of movies that match the given parameters
	 */
//	@Override
//	public List<OnboardMovieResponse> filterMovies(String title, String location, String genre) {
//		log.info(
//			"Received request for iltering movies based on the given parameters; title: {}, location: {}, genre: {}",
//			title, location, genre);
		// step-1: get all movies
//	    List<Movie> movies = movieRepository.findAll();
//		// step-2: create a predicate for each parameter
//		Predicate<Movie> titleChecker =
//			movie -> title != null && movie.getTitle().toLowerCase().contains(title.toLowerCase());
//		Predicate<Movie> locationChecker =
//			movie -> location != null && movie.getTheatre().getLocation().toLowerCase().contains(location.toLowerCase());
//		Predicate<Movie> genreChecker =
//			movie -> genre != null && movie.getGenre().toLowerCase().contains(genre.toLowerCase());
//		// step-3: filter the movies based on the predicates evaluated
//		return movies.stream().filter(titleChecker.and(locationChecker).and(genreChecker))
//			.collect(Collectors.toList());
//	}
//
//	@Override
//	public List<OnboardMovieResponse> getAllMovies(String title, String location, String genre) {
//		log.info(
//			"Received request for getting all movies based on the given parameters; title: {}, location: {}, genre: {}",
//			title, location, genre);
//		if (title == null && location == null && genre == null) {
//			return onboardMovieRepository.findAllBy();
//		} else {
//			return filterMovies(title,  location, genre, );
//		}
//	}

	@Override
	public Movie getMovieById(@PathVariable("id") Integer id) {
		log.info("Received request for getting the movie details by the given Id; movieId: {}", id);
		return movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid movie ID: " + id));
	}

	
	@Override
	public String addMovie(@RequestBody NewMovieRequest request) {
		try {
			Movie movie = modelMapper.map(request, Movie.class);
			movie.setCastCrew(objectMapper.writeValueAsString(request.getCastCrew()));
			movieRepository.save(movie);
			return "Movie added successfully";
		} catch (Exception e) {
			log.error("Error occured while adding theater request: {}, error: {}", new Gson().toJson(request),
					e.getMessage());
			return null;
		}
	}

	@Override
	public void deleteMovie(@PathVariable("movieId") Integer id) {
		Movie movie =
			movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid movie ID: " + id));
		movieRepository.delete(movie);
	}

	@Override
	public void updateMovie(@PathVariable("movieId") Integer id, @RequestBody NewMovieRequest request) {
		// step-1: Just a sanity check if the movie exists with the given id
		movieRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid movie ID: " + id));
		// step-2: map the request to the movie entity
		Movie movie = modelMapper.map(request, Movie.class);
		// step-3: save the updated movie entity
		movieRepository.save(movie);
	}

	@Override
	public List<MovieResponse> getAllMovies() {
		return movieRepository.findAllBy();
	}

	@Override
	public List<Movie> filterMovies(String title, String location, String genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MovieResponse> getAllMovies(String title, String location, String genre) {
		// TODO Auto-generated method stub
		return null;
	}
}
