package com.example.HotelBookingApp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HotelBookingApp.model.Rooms;

public interface RoomsRepo extends JpaRepository<Rooms, Long> {
	Optional<List<Rooms>> findAllByHotelId(Long hotel_id);

}
