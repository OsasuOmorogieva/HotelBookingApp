package com.example.HotelBookingApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.HotelBookingApp.Repository.RoomsRepo;
import com.example.HotelBookingApp.model.Hotels;
import com.example.HotelBookingApp.model.Rooms;


@Service
public class RoomService {
	
	private Hotels hotel;
	@Autowired
	RoomsRepo roomRepo;

	public List<Rooms> getAllRooms(){
		return roomRepo.findAll();
	}

	public void addRoom(Rooms room) {
		room.setHotel(hotel);
		roomRepo.save(room);
		
	}

}
