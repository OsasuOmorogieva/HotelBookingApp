package com.example.HotelBookingApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotelBookingApp.DTO.HotelDTO;
import com.example.HotelBookingApp.Service.HotelService;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class HotelController {
	
	@Autowired
	HotelService hotelService;
	
	@GetMapping("/viewAllHotels")
	public ResponseEntity<List<HotelDTO>> viewAllHotels(){
		List<HotelDTO> hotels = hotelService.viewAllHotels();
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}
}
