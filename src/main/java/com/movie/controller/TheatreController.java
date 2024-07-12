package com.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.movie.projection.TheatreResponse;
import com.movie.request.TheaterRequest;
import com.movie.service.TheatreService;

import lombok.extern.slf4j.Slf4j;

@RestController("/theatre")
@RequestMapping
@Slf4j
public class TheatreController {

	@Autowired
	private TheatreService theatreService;
	
	@PostMapping("/theatre")
	public String addTheatre(@RequestBody TheaterRequest request) {
		log.info("Received request to theatre with details: {}",new Gson().toJson(request));
		return theatreService.addTheatre(request);
	}
	
	@GetMapping("/theatre")
	public List<TheatreResponse> getAllTheatre() {
		log.info("Received request to get all theatres");
		return theatreService.getAllTheatres();
	}
	
	
}
