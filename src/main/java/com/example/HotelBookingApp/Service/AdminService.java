package com.example.HotelBookingApp.Service;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HotelBookingApp.Repository.HotelRepository;
import com.example.HotelBookingApp.Repository.RoomsRepo;
import com.example.HotelBookingApp.Repository.UserRepository;
import com.example.HotelBookingApp.model.Hotels;
import com.example.HotelBookingApp.model.Rooms;
import com.example.HotelBookingApp.model.Users;

@Service
public class AdminService {
	
	
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired 
	HotelRepository hotelRepo;
	
	@Autowired
	RoomsRepo roomRepo;

	public Hotels addHotel(Hotels hotel) throws AccessDeniedException {
	    if (hotel.getOwner() == null || hotel.getOwner().getId()== null) {
	        throw new IllegalArgumentException("Owner ID is missing in the request.");
	    }
	    Long ownerId = hotel.getOwner().getId();
	    Optional<Hotels> existingHotel = hotelRepo.findByNameIgnoreCaseAndAddressIgnoreCaseAndCityIgnoreCase(
	    	    hotel.getName().toLowerCase(), hotel.getAddress().toLowerCase(), hotel.getCity().toLowerCase()
	    	);

	    if (existingHotel.isPresent()) {
	        System.out.println("Hotel with same details already exists");
	        return null;
	    }

	    Users user = userRepo.findById(ownerId)
	            .orElseThrow(() -> new RuntimeException("Owner not found."));

	    if (!user.getRole().equalsIgnoreCase("admin")) {
	        throw new AccessDeniedException("User does not have permission to add a hotel.");
	    }

	    hotel.setOwner(user); // Reattach the managed entity

	    return hotelRepo.save(hotel);
	}


	public Rooms addRoom(Rooms room) {
		if(room.getHotel()==null || room.getHotel().getId()==null) {
			throw new IllegalArgumentException("Hotel ID is missing in the request.");
		}
		Long hotelId = room.getHotel().getId();
		Optional<Hotels> hotel = hotelRepo.findById(hotelId);
		room.setHotel(hotel.get());
		room.setType(room.getType());
		room.setDescription(room.getDescription());
		room.setPrice(room.getPrice());
		room.setMaxGuests(room.getMaxGuests());
		room.setAmenities(room.getAmenities());
		room.setIsAvailable(true);
		roomRepo.save(room);
			return room;
	}

}
