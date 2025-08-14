package com.example.HotelBookingApp.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.HotelBookingApp.model.Bookings;

public interface BookingRepository extends JpaRepository<Bookings, Long> {
	Optional<List<Bookings>>findByRoomId(Long roomId);
	 @Query("SELECT b FROM Bookings b WHERE b.room.id = :roomId " +
	           "AND b.status = 'confirmed' " +
	           "AND b.checkIn < :newCheckOut " +
	           "AND b.checkOut > :newCheckIn")
	    List<Bookings> findConflictingBookings(
	            @Param("roomId") Long roomId,
	            @Param("newCheckIn") LocalDate newCheckIn,
	            @Param("newCheckOut") LocalDate newCheckOut
	    );
	

}
