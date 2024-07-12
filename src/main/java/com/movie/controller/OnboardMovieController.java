package com.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movie.projection.OnboardMovieResponse;
import com.movie.request.OnboardMovieRequest;
import com.movie.service.OnboardMovieService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController("/onboardmovie")
@RequestMapping
@Slf4j
public class OnboardMovieController {

	@Autowired
	private OnboardMovieService onboardMovieService;
	
	@PostMapping(value = "/onboardmovie")
	@ApiOperation(value = "Onboard movies", notes = "Onboard movies")
	public String onboardMovie(@RequestBody OnboardMovieRequest request) {
		//log.info("Received request to onboard a movie with details: {}",new Gson().toJson(request));
		return onboardMovieService.onboardMovie(request);		
	}
	
	@GetMapping(value = "/onboardedmovie")
	@ApiOperation(value = "Onboard movies", notes = "Onboard movies")
	public List<OnboardMovieResponse> getOnboardedMovies(@RequestParam(required = false) String location, @RequestParam(required = false) String genre) {
		log.info("Received request to get onboarded a movies with location: {}, genre : {}",location, genre);
		return onboardMovieService.getOnboardedMovies(location, genre);		
	}
}
