package com.example.HotelBookingApp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HotelBookingApp.model.Reviews;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

	Optional<Reviews> findByUserIdAndHotelId(Long UserId, Long hotelId);

	List<Reviews> findByHotelId(Long hotelId);

}
