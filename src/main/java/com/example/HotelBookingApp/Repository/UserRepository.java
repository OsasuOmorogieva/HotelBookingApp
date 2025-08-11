package com.example.HotelBookingApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HotelBookingApp.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
	//Optional<Users> findByOwnerId(Users ownerId);
	Optional<Users> findByUsername(String username);
	Optional<Users> findByEmail(String email);

}
