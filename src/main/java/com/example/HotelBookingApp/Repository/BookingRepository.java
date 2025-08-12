package com.example.HotelBookingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HotelBookingApp.model.Bookings;

public interface BookingRepository extends JpaRepository<Bookings, Long> {

}
