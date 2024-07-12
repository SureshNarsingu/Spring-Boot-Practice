package com.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.model.Bookings;
import com.movie.projection.BookingResponse;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Integer> {

	List<BookingResponse> findByMobileNo(String mobileNo);

}
