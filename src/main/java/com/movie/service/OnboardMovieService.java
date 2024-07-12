package com.movie.service;

import java.util.List;

import com.movie.projection.OnboardMovieResponse;
import com.movie.request.OnboardMovieRequest;

public interface OnboardMovieService {
	
	public String onboardMovie(OnboardMovieRequest request);

	public List<OnboardMovieResponse> getOnboardedMovies(String location, String genre);
}
