package com.movie.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.model.Movie;
import com.movie.projection.MovieResponse;
import com.movie.request.NewMovieRequest;
import com.movie.service.impl.MovieServiceImpl;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * This acts as the controller for the Movie related operations
 */
@RestController
@RequestMapping("/movie")
@Slf4j
public class MovieController {

	private final MovieServiceImpl movieService;

	public MovieController(MovieServiceImpl movieService) {
		this.movieService = movieService;
	}

	@GetMapping
	@ApiOperation(value = "Get list of movies available", notes = "Get list of movies available")
	public List<MovieResponse> getMovies(@RequestParam(required = false) String title,
		@RequestParam(required = false) String location, @RequestParam(required = false) String genre) {
		return movieService.getAllMovies(title, location, genre);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retrieve the movie details by the given Id", notes = "Retrieve the movie by the given Id")
	public Movie getMovieById(@PathVariable("id") Integer id) {
		return movieService.getMovieById(id);
	}

	@PostMapping
	@ApiOperation(value = "Add a new movie entry", notes = "Add a new movie entry")
	public String addMovie(@RequestBody NewMovieRequest movie) {
		return movieService.addMovie(movie);
	}
	
	@GetMapping(value = "/all")
	@ApiOperation(value = "Get all list of movies available", notes = "Get all list of movies available")
	public List<MovieResponse> getAllMovies() {
		return movieService.getAllMovies();
	}

	@DeleteMapping("/{movieId}")
	@ApiOperation(value = "Delete the movie by the given Id", notes = "Delete the movie by the given Id")
	public void deleteMovie(@PathVariable("movieId") Integer id) {
		movieService.deleteMovie(id);
	}

	@PutMapping("/{movieId}")
	@ApiOperation(value = "Update the movie by the given Id", notes = "Update the movie by the given Id")
	public void updateMovie(@PathVariable("movieId") Integer id, @RequestBody NewMovieRequest movie) {
		movieService.updateMovie(id, movie);
	}
}
