package com.movie.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_theatre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Theatre {
	@Id
	@SequenceGenerator(name = "theatre_id_sequence", sequenceName = "sq_theatre", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "theatre_id_sequence")
	private int id;
	private String name;
	private String location;
	private int totalSeats;
	private String seatPriceRange;
	private String showTimings;

}
