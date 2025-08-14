package com.example.HotelBookingApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HotelBookingApp.model.Wishlists;

public interface WishlistsRepository extends JpaRepository<Wishlists, Long> {

	List<Wishlists> findByUserId(Long userId);

	void deleteByHotelId(Long hotelId);



}
