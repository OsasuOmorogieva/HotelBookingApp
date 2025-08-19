package com.example.HotelBookingApp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.HotelBookingApp.model.Rooms;

public interface RoomsRepo extends JpaRepository<Rooms, Long> {
	Optional<List<Rooms>> findAllByHotelId(Long hotel_id);

	@Query("SELECT r FROM Rooms r WHERE "+
			"LOWER(r.type) LIKE LOWER(CONCAT('%', : keyword, '%')) OR "
			+ "LOWER(r.description) LIKE(CONCAT('%', : keyword, '%')) OR "
			+ "LOWER(r.amenities) LIKE LOWER(CONCAT('%', : keyword, '%')) ")	
	List<Rooms> searchRooms(@Param("keyword")String keyword);

	@Query("SELECT r FROM Rooms r WHERE r.price = :price")
	List<Rooms> searchRoomsbyPrice(@Param("price")double price);
	
	@Query("SELECT r FROM Rooms r WHERE r.maxGuests = :maxGuest")
	List<Rooms> searchRoomsbyMaxGuest(@Param("maxGuests")int maxGuests);
}
