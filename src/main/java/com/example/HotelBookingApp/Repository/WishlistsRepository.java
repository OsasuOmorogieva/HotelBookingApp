package com.example.HotelBookingApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.HotelBookingApp.model.Wishlists;

public interface WishlistsRepository extends JpaRepository<Wishlists, Long> {

}
