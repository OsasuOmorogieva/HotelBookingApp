package com.example.HotelBookingApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotelBookingApp.Service.UserService;
import com.example.HotelBookingApp.model.Hotels;
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
	@PostMapping("/addWishlist")
	public ResponseEntity<String> addWishlist(@RequestBody Hotels hotel){
		String newWishlist = userService.addWishList(hotel);
		return new ResponseEntity<>(newWishlist, HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
}
