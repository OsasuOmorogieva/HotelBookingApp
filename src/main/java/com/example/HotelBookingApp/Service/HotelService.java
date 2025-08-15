package com.example.HotelBookingApp.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.HotelBookingApp.DTO.DateRange;
import com.example.HotelBookingApp.DTO.HotelDTO;
import com.example.HotelBookingApp.Repository.BookingRepository;
import com.example.HotelBookingApp.Repository.HotelRepository;
import com.example.HotelBookingApp.Repository.ReviewsRepository;
import com.example.HotelBookingApp.Repository.RoomsRepo;
import com.example.HotelBookingApp.Repository.UserRepository;
import com.example.HotelBookingApp.Repository.WishlistsRepository;
import com.example.HotelBookingApp.exception.HotelNotFoundException;
import com.example.HotelBookingApp.exception.UserNotFoundException;
import com.example.HotelBookingApp.model.Bookings;
import com.example.HotelBookingApp.model.Hotels;
import com.example.HotelBookingApp.model.Reviews;
import com.example.HotelBookingApp.model.Rooms;
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
	@Autowired
	ReviewsRepository reviewRepo;
	
	
	@Autowired
	BookingRepository bookingRepo;
	
	@Autowired
	RoomsRepo roomRepo;
	
	
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
	 public String addReview(Reviews review) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Users user = userRepo.findByUsername(username).orElseThrow(()-> new UserNotFoundException(String.format("username does not exist", username)));
			Long hotelId = review.getHotel().getId();
			Optional<Reviews> oldReview = reviewRepo.findByUserIdAndHotelId(user.getId(), hotelId);
			if(oldReview.isPresent()) {
				return "User can only add one review per hotel, delete previous review to add new one";
			}
			Hotels hotel = hotelRepo.findById(hotelId).orElseThrow(()->new HotelNotFoundException(String.format("Hotel does not exist", review.getHotel())));
			review.setUser(user);
			review.setHotel(hotel);
			review.setComment(review.getComment());
			review.setRating(review.getRating());
			review.setCreatedAt(LocalDateTime.now());
			reviewRepo.save(review);
			return "Thank you for your review";
		}
	 public List<Reviews> getReviews(Long hotelId){
		List<Reviews> reviews =reviewRepo.findByHotelId(hotelId);
		 return reviews;
	 }
	 
	 public List<Rooms> getAvailableRooms(DateRange request) throws NotFoundException {
		 
		 List<Rooms> conflictRooms = new ArrayList<>(1000000);
		 List<Rooms> availableRooms = new ArrayList<>(1000000);
		 List<Rooms> allRooms = roomRepo.findAll();
		Optional<List<Bookings>> optConflictingBookings = bookingRepo.findConflictingDates(request.getCheckIn(), request.getCheckOut());
		if(optConflictingBookings.isEmpty()) {
			throw new NotFoundException();
		}
		List<Bookings> conflictingBookings = optConflictingBookings.get();
		for(Bookings conflict : conflictingBookings) {
			Rooms conflictRoom = conflict.getRoom();
			conflictRooms.add(conflictRoom);
		}
		for(Rooms room : allRooms) {
			if(!conflictRooms.contains(room)) {
				availableRooms.add(room);
			}
		}
		return availableRooms;
	 }
}
