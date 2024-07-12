package com.movie.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

	@Id
	@SequenceGenerator(name = "movie_id_sequence", sequenceName = "sq_movie", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_id_sequence")
	private int id;
	private String title;
	@Column(name = "cast_crew")
	private String castCrew;
	private String description;
	private String genre;
	private LocalDate releaseDate;
}
