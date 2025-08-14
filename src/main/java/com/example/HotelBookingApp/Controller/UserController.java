package com.example.HotelBookingApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotelBookingApp.Service.UserService;
import com.example.HotelBookingApp.model.Users;
import com.example.HotelBookingApp.model.Wishlists;
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
	
	@GetMapping("/getWishlist")
	public ResponseEntity<List<Wishlists>> getWishlist(){
		List<Wishlists> wishlist =userService.getWishlist();
		return new ResponseEntity<>(wishlist, HttpStatus.OK);
	}
	
	
}
