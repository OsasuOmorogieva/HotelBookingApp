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
import com.example.HotelBookingApp.DTO.RoomDTO;
import com.example.HotelBookingApp.Service.UserService;
import com.example.HotelBookingApp.model.Rooms;
import com.example.HotelBookingApp.model.Users;

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
	
	@GetMapping("/viewAllHotels")
	public ResponseEntity<List<HotelDTO>> viewAllHotels(){
		List<HotelDTO> hotels = userService.viewAllHotels();
		return new ResponseEntity<>(hotels, HttpStatus.OK);
	}
	@GetMapping("/getAllRooms/{hotel_id}")
		public ResponseEntity<List<RoomDTO>> getAllRooms(@PathVariable Long hotel_id) throws NotFoundException{
			List<RoomDTO> rooms = userService.getAllRooms(hotel_id);
			return new ResponseEntity<>(rooms, HttpStatus.OK);
			
		}
	@GetMapping("/getRoom/{id}")
	public ResponseEntity<RoomDTO> getRoom(@PathVariable Long id) throws NotFoundException{
		RoomDTO roomDetails =userService.getRoom(id);
		return new ResponseEntity<>(roomDetails, HttpStatus.OK);
	}
	
	@GetMapping("/bookRoom/{hotel_id}/{room_id}")
	public ResponseEntity<?> bookRoom(@PathVariable Long hotel_id, @PathVariable Long room_id) throws NotFoundException{
		 String bookingUpdate = userService.bookRoom(hotel_id, room_id);
		return new ResponseEntity<>( bookingUpdate, HttpStatus.OK);
	}
	
	@GetMapping("/availableRoom/{hotel_id}")
	public ResponseEntity<List<Rooms>> availableRoomInHotel(@PathVariable Long hotel_id){
		List<Rooms> availableRooms = userService.availableRoomInHotel(hotel_id);
		return new ResponseEntity<>(availableRooms, HttpStatus.OK);
	}
}
