package com.example.HotelBookingApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotelBookingApp.Service.RoomService;
import com.example.HotelBookingApp.model.Rooms;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class RoomController {
	
	@Autowired
	RoomService roomService;
	
	@GetMapping("/getRooms")
	public ResponseEntity<List<Rooms>> viewAvailableRooms(){
		return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
	}

	public ResponseEntity<String> addRoom(@RequestBody Rooms room){
		roomService.addRoom(room);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
