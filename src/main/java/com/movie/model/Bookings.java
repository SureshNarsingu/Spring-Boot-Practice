package com.movie.model;

import java.time.LocalDate;

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
@Table(name = "tb_bookings", schema = "cla")
@NoArgsConstructor
@AllArgsConstructor
public class Bookings {
	@Id
	@SequenceGenerator(name = "booking_id_sequence", sequenceName = "sq_booking", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_id_sequence")
	private int id;
	@Column(name = "movie_name")
	private String movieName;
	private String location;
	@Column(name = "reserved_seat")
	private String reservedSeat;
	private int totalPayment;
	private LocalDate bookingDate;
	private String showTimings;
	@Column(name = "mobile_number")
	private String mobileNo;
	@OneToOne(fetch = FetchType.LAZY, targetEntity = Theatre.class)
	@JoinColumn(name = "theatre_id", referencedColumnName = "id")
	private Theatre theatre;

}
