package com.example.HotelBookingApp.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HotelBookingApp.DTO.HotelDTO;
import com.example.HotelBookingApp.Repository.HotelRepository;
import com.example.HotelBookingApp.model.Hotels;

@Service
public class HotelService {
	@Autowired
	HotelRepository hotelRepo;
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
}
