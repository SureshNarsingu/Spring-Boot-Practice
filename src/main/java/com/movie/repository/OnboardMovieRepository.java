package com.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.model.OnboardMovie;
import com.movie.projection.OnboardMovieResponse;

@Repository
public interface OnboardMovieRepository extends JpaRepository<OnboardMovie, Integer> {

	List<OnboardMovieResponse> findAllBy();

//	OnboardMovieResponse saveBy(OnboardMovie onboardMovie);

}
