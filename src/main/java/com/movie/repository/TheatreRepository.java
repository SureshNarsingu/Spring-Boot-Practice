package com.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.model.Theatre;
import com.movie.projection.TheatreResponse;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Integer>{

	List<TheatreResponse> findAllBy();
	
}
