package com.movie.service;

import java.util.List;

import com.movie.projection.TheatreResponse;
import com.movie.request.TheaterRequest;

public interface TheatreService {

	public String addTheatre(TheaterRequest request);
	
	public List<TheatreResponse> getAllTheatres();
}
