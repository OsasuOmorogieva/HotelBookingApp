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

import com.example.HotelBookingApp.DTO.RoomDTO;
import com.example.HotelBookingApp.Service.RoomService;
import com.example.HotelBookingApp.model.Bookings;
import com.example.HotelBookingApp.model.Payments;
import com.example.HotelBookingApp.model.Rooms;

@RestController
@RequestMapping("/rooms")
@CrossOrigin
public class RoomController {
	
	@Autowired
	RoomService roomService;
	
	@GetMapping("/getAllRooms/{hotel_id}")
	public ResponseEntity<List<RoomDTO>> getAllRooms(@PathVariable Long hotel_id) throws NotFoundException{
		List<RoomDTO> rooms = roomService.getAllRooms(hotel_id);
		return new ResponseEntity<>(rooms, HttpStatus.OK);
		
	}
@GetMapping("/getRoom/{id}")
public ResponseEntity<RoomDTO> getRoom(@PathVariable Long id) throws NotFoundException{
	RoomDTO roomDetails =roomService.getRoom(id);
	return new ResponseEntity<>(roomDetails, HttpStatus.OK);
}
@GetMapping("/availableRoom/{hotel_id}")
public ResponseEntity<List<Rooms>> availableRoomInHotel(@PathVariable Long hotel_id){
	List<Rooms> availableRooms = roomService.availableRoomInHotel(hotel_id);
	return new ResponseEntity<>(availableRooms, HttpStatus.OK);
}
@PostMapping("/bookRoom")
public ResponseEntity<?> bookRoom(@RequestBody Bookings booking) throws NotFoundException{
	 String bookingUpdate = roomService.bookRoom(booking);
	return new ResponseEntity<>( bookingUpdate, HttpStatus.OK);
}
@PostMapping("/cancelBooking")
public ResponseEntity<String> cancelBooking(@RequestBody Bookings booking) throws NotFoundException{
	String bookingUpdate = roomService.cancelBooking(booking);
	return new ResponseEntity<>(bookingUpdate, HttpStatus.OK);
}
@PostMapping("/payment")
public ResponseEntity<String> payment(@RequestBody Payments payment) throws NotFoundException{
	String paymentUpdate = roomService.payment(payment);
	return new ResponseEntity<>(paymentUpdate, HttpStatus.OK);
}

}
