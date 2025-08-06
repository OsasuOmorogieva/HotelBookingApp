package com.example.HotelBookingApp.Controller;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotelBookingApp.Service.AdminService;
import com.example.HotelBookingApp.model.Hotels;
import com.example.HotelBookingApp.model.Rooms;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	@PostMapping("/addHotel")
	public ResponseEntity<?> addHotel(@RequestBody Hotels hotel) throws AccessDeniedException{
		Hotels newHotel = adminService.addHotel(hotel);
		return new ResponseEntity<>(newHotel, HttpStatus.CREATED);
	}
	@PostMapping("/addRoom")
	public ResponseEntity<?> addRoom(@RequestBody Rooms room){
		Rooms newRoom = adminService.addRoom(room);
		return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
	}

}
