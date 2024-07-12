package com.movie.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_onboardmovie")
@NoArgsConstructor
@AllArgsConstructor
public class OnboardMovie {

	@Id
	@SequenceGenerator(name = "onboardmovie_id_sequence", sequenceName = "sq_onboardmovie", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "onboardmovie_id_sequence")
	private int id;
	
	@Column(name = "onboardmovie_date")
	private LocalDate onboardMovieDate;
	
	@Column(name = "show_timings")
	private String showTimings;
	
	@OneToOne(fetch = FetchType.LAZY, targetEntity = Movie.class)
	@JoinColumn(name = "movie_id", referencedColumnName = "id")
	private Movie movie;
	
	@OneToOne(fetch = FetchType.LAZY, targetEntity = Theatre.class)
	@JoinColumn(name = "theatre_id", referencedColumnName = "id")
	private Theatre theatre;
	
}
