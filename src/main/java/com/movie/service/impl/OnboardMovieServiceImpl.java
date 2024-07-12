package com.movie.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.movie.model.Movie;
import com.movie.model.OnboardMovie;
import com.movie.model.Theatre;
import com.movie.projection.OnboardMovieResponse;
import com.movie.repository.MovieRepository;
import com.movie.repository.OnboardMovieRepository;
import com.movie.repository.TheatreRepository;
import com.movie.request.OnboardMovieRequest;
import com.movie.service.OnboardMovieService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OnboardMovieServiceImpl implements OnboardMovieService {
	
	@Autowired
	private OnboardMovieRepository onboardMovieRepository;
	
	@Autowired
	private MovieRepository  movieRepository;
	
	@Autowired
	private TheatreRepository theatreRepository;

	@Override
	public String onboardMovie(OnboardMovieRequest request) {
		try {
			final OnboardMovie onboardMovie = new OnboardMovie();
			Optional<Theatre> optionalTheatre = theatreRepository.findById(request.getTheatreId());
			Optional<Movie> optionalMovie = movieRepository.findById(request.getMovieId());
			if (optionalTheatre.isPresent() && optionalMovie.isPresent()) {
				onboardMovie.setMovie(optionalMovie.get());
				onboardMovie.setTheatre(optionalTheatre.get());
				onboardMovie.setOnboardMovieDate(request.getDate());;
				onboardMovie.setShowTimings(request.getShowTimings());
				onboardMovieRepository.save(onboardMovie);		
			}
			return "Movie onboarded successfully";
		} catch (Exception e) {
			log.error("Error occured while onboarding movie request: {}, error: {}", new Gson().toJson(request),
					e.getMessage());
			return null;
		}
	}

	@Override
	public List<OnboardMovieResponse> getOnboardedMovies(String location, String genre) {
		log.info(
				"Received request for getting all movies based on the given parameters; title: {}, location: {}, genre: {}",
				location, genre);
		if (location == null && genre == null) {
			return onboardMovieRepository.findAllBy();
		} else {
			return filterMovies(location, genre);
		}
	}

	public List<OnboardMovieResponse> filterMovies(String location, String genre) {
		List<OnboardMovieResponse> onboardedMovie = onboardMovieRepository.findAllBy();
		Predicate<OnboardMovieResponse> genreChecker = onboardMovieResponse -> genre != null
				&& onboardMovieResponse.getMovie().getGenre().toLowerCase().equalsIgnoreCase(genre.toLowerCase());
		Predicate<OnboardMovieResponse> locationChecker = onboardMovieResponse -> location != null
				&& onboardMovieResponse.getTheatre().getLocation().toLowerCase().equalsIgnoreCase(location.toLowerCase());
		return onboardedMovie.stream().filter(genreChecker.or(locationChecker)).collect(Collectors.toList());
	}

	

}
