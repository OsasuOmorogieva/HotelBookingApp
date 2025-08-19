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

import com.example.HotelBookingApp.DTO.DateRange;
import com.example.HotelBookingApp.DTO.HotelDTO;
import com.example.HotelBookingApp.Service.HotelService;
import com.example.HotelBookingApp.model.Hotels;
import com.example.HotelBookingApp.model.Reviews;
import com.example.HotelBookingApp.model.Rooms;

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
	@PostMapping("/addWishlist")
	public ResponseEntity<String> addWishlist(@RequestBody Hotels hotel){
		String newWishlist = hotelService.addWishList(hotel);
		return new ResponseEntity<>(newWishlist, HttpStatus.CREATED);
	}
	
	@PostMapping("/removeWishlist")
	public ResponseEntity<String> removeWishlist(@RequestBody Hotels hotel){
		String update = hotelService.removeWishList(hotel);
		return new ResponseEntity<>(update, HttpStatus.OK);
	}
	@PostMapping("/addReview")
	public ResponseEntity<?> addReview(@RequestBody Reviews review){
		String update = hotelService.addReview(review);
		return new ResponseEntity<>(update, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/reviews/{hotelId}")
	public ResponseEntity<List<Reviews>> getReviews(@PathVariable Long hotelId){
		List<Reviews> hotelReviews =hotelService.getReviews(hotelId);
		return new ResponseEntity<>(hotelReviews, HttpStatus.FOUND);
	}
	
	@PostMapping("/getAvailableRooms")
	public ResponseEntity<List<Rooms>> getAvailableRooms(@RequestBody DateRange request) throws NotFoundException{
		List<Rooms> availableRooms = hotelService.getAvailableRooms( request);
		return new ResponseEntity<>(availableRooms, HttpStatus.OK);
	}
}
