package com.example.HotelBookingApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HotelBookingApp.model.Payments;

public interface PaymentRepository extends JpaRepository<Payments, Long> {
	Optional<Payments> findByBookingId(Long bookingId);

}
