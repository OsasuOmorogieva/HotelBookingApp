package com.example.HotelBookingApp.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HotelBookingApp.DTO.HotelDTO;
import com.example.HotelBookingApp.Repository.HotelRepository;
import com.example.HotelBookingApp.Repository.UserRepository;
import com.example.HotelBookingApp.Repository.WishlistsRepository;
import com.example.HotelBookingApp.exception.UserNotFoundException;
import com.example.HotelBookingApp.model.Hotels;
import com.example.HotelBookingApp.model.Users;
import com.example.HotelBookingApp.model.Wishlists;

@Service
public class HotelService {
	@Autowired
	HotelRepository hotelRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	WishlistsRepository wishlistRepo;
	
	
	public List<HotelDTO> viewAllHotels() {
		
		

		List<Hotels> hotels = hotelRepo.findAll();
		return hotels.stream().map(hotel -> new HotelDTO(
				hotel.getName(),
				hotel.getDescription(),
				hotel.getAddress(),
				hotel.getCity(), 
				hotel.getRating()))
				.collect(Collectors.toList());
	}
	public String addWishList(Hotels hotel){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Users user = userRepo.findByUsername(username).orElseThrow(()-> new UserNotFoundException(String.format("username does not exist", username)));
		Long userId = user.getId();
		Wishlists wishlist =new Wishlists();
		List<Wishlists> wishlists = wishlistRepo.findByUserId(userId);
				
		for(Wishlists wish : wishlists) {
			if(hotel.getName().equalsIgnoreCase(wish.getHotel().getName()) && hotel.getCity().equalsIgnoreCase(wish.getHotel().getCity())) {
				return String.format("%s %s is already added to your wishlist", hotel.getName(), hotel.getCity());
			}
		}
		
		Hotels wishedHotel = hotelRepo.findByNameIgnoreCaseAndCityIgnoreCase(hotel.getName(), hotel.getCity()).orElseThrow(()-> new UserNotFoundException(String.format("Hotel does not exist", hotel.getName())));
		wishlist.setUser(user);
		wishlist.setHotel(wishedHotel);
		wishlist.setCreatedAt(Timestamp.from(Instant.now()));
		wishlistRepo.save(wishlist);
		return "WishList updated successfully";
		
	}
	 @Transactional
	public String removeWishList(Hotels hotel) {
		Hotels removeHotel = hotelRepo.findByNameIgnoreCaseAndCityIgnoreCase(hotel.getName(), hotel.getCity()).orElseThrow(()-> new UserNotFoundException(String.format("Hotel does not exist", hotel.getName())));
		Long hotelId = removeHotel.getId();
		wishlistRepo.deleteByHotelId(hotelId);
		return String.format("%s %s has been removed from wishlist successfully", hotel.getName(), hotel.getCity());
	}
}
