package com.movie.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.movie.model.Theatre;
import com.movie.projection.TheatreResponse;
import com.movie.repository.TheatreRepository;
import com.movie.request.TheaterRequest;
import com.movie.service.TheatreService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TheatreServiceImpl implements TheatreService {

	// field injection of the ModelMapper
	@Autowired
	ModelMapper modelMapper;

	private final TheatreRepository theatreRepository;
	
	@Autowired
	private ObjectMapper objectMapper;

	public TheatreServiceImpl(TheatreRepository theatreRepository) {
		this.theatreRepository = theatreRepository;
	}

	@Override
	public String addTheatre(TheaterRequest request) {
		try {
			Theatre theatre = modelMapper.map(request, Theatre.class);
			theatre.setSeatPriceRange(objectMapper.writeValueAsString(request.getSeatPriceRange()));
			theatre.setShowTimings(objectMapper.writeValueAsString(request.getShowTimings()));
			theatreRepository.save(theatre);
			return "theatre added successfully";
		} catch (Exception e) {
			log.error("Error occured while adding theater request: {}, error: {}", new Gson().toJson(request),
					e.getMessage());
			return null;
		}

	}

	@Override
	public List<TheatreResponse> getAllTheatres() {
		return theatreRepository.findAllBy();
	}

}
