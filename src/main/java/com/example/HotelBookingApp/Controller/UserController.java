package com.example.HotelBookingApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotelBookingApp.DTO.HotelDTO;
import com.example.HotelBookingApp.Service.UserService;
import com.example.HotelBookingApp.model.Users;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Users> register(@RequestBody Users user){
		Users newUser = userService.register(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Users user){
		String token = userService.login(user);
		 return new ResponseEntity<>(token,HttpStatus.OK);
	}
	@PostMapping("/resetPassword")
	public ResponseEntity<?> resetPassword (@RequestBody JsonNode json){
		userService.resetPassword(json.get("password").asText());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PostMapping("/sendResetPasswordEmail")
	public ResponseEntity<?> sendResetPasswordEmail(@RequestBody JsonNode json){
		userService.sendResetPasswordEmail(json.get("email").asText());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/viewAllHotels")
	public ResponseEntity<List<HotelDTO>> viewAllHotels(){
		List<HotelDTO> hotels = userService.viewAllHotels();
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}
	
	
	@GetMapping("/bookRoom/{hotel_id}/{room_id}")
	public ResponseEntity<?> bookRoom(@PathVariable Long hotel_id, @PathVariable Long room_id) throws NotFoundException{
		 String bookingUpdate = userService.bookRoom(hotel_id, room_id);
		return new ResponseEntity<>( bookingUpdate, HttpStatus.OK);
	}
	
	
	
	
}
