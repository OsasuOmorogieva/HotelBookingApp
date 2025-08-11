package com.example.HotelBookingApp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.HotelBookingApp.DTO.HotelDTO;
import com.example.HotelBookingApp.DTO.RoomDTO;
import com.example.HotelBookingApp.Repository.RoomsRepo;
import com.example.HotelBookingApp.model.Hotels;
import com.example.HotelBookingApp.model.Rooms;


@Service
public class RoomService {
	
	@Autowired
	RoomsRepo roomRepo;

	public List<RoomDTO> getAllRooms(Long hotel_id) throws NotFoundException {
		Optional<List<Rooms>> rooms = roomRepo.findAllByHotelId(hotel_id);

		if (rooms.isEmpty()) {
			throw new NotFoundException();
		}

		List<Rooms> hotelRooms = rooms.get();

		return hotelRooms.stream().map(room -> {
			Hotels hotel = room.getHotel();

			HotelDTO hotelDTO = new HotelDTO(
					hotel.getName(),
					hotel.getAddress(),
					hotel.getDescription(),
					hotel.getCity(),
					hotel.getRating());

			return new RoomDTO(
					room.getId(), 
					hotelDTO,
					room.getType(), 
					room.getDescription(),
					room.getPrice(),
					room.getMaxGuests(), 
					room.getAmenities(),
					room.getIsAvailable());
			}).collect(Collectors.toList());
	}

	public RoomDTO getRoom(Long id) throws NotFoundException {
		Rooms roomDetails = roomRepo.findById(id).orElseThrow(() -> new NotFoundException());
		Hotels hotel = roomDetails.getHotel();
		HotelDTO hotelDTO = new HotelDTO(
				hotel.getName(), 
				hotel.getAddress(), 
				hotel.getDescription(),
				hotel.getCity(),
				hotel.getRating());

		return new RoomDTO(
				roomDetails.getId(),
				hotelDTO,
				roomDetails.getType(),
				roomDetails.getDescription(),
				roomDetails.getPrice(),
				roomDetails.getMaxGuests(),
				roomDetails.getAmenities(),
				roomDetails.getIsAvailable());

	}

	public List<Rooms> availableRoomInHotel(Long hotel_id) {
		List<Rooms> hotelRooms = roomRepo.findAllByHotelId(hotel_id).get();
		List<Rooms> availableRooms = new ArrayList<Rooms>();
		for(Rooms room : hotelRooms) {
			if(room.getIsAvailable() == true) {
				availableRooms.add(room);
			}
		}
		return availableRooms;
	}
}
