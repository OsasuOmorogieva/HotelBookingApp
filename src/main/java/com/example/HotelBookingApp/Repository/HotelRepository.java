package com.example.HotelBookingApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HotelBookingApp.model.Hotels;

public interface HotelRepository extends JpaRepository<Hotels, Long> {
	Optional<Hotels> findByOwnerId(Long ownerId);

	Optional<Hotels> findByNameIgnoreCaseAndAddressIgnoreCaseAndCityIgnoreCase(String name, String address, String city);
	Optional<Hotels> findByNameIgnoreCaseAndCityIgnoreCase(String name, String city);



}
