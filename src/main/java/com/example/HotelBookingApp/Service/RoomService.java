package com.example.HotelBookingApp.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.HotelBookingApp.DTO.HotelDTO;
import com.example.HotelBookingApp.DTO.RoomDTO;
import com.example.HotelBookingApp.Repository.BookingRepository;
import com.example.HotelBookingApp.Repository.PaymentRepository;
import com.example.HotelBookingApp.Repository.RoomsRepo;
import com.example.HotelBookingApp.Repository.UserRepository;
import com.example.HotelBookingApp.model.Bookings;
import com.example.HotelBookingApp.model.Hotels;
import com.example.HotelBookingApp.model.Payments;
import com.example.HotelBookingApp.model.Rooms;
import com.example.HotelBookingApp.model.Users;


@Service
public class RoomService {
	
	@Autowired
	RoomsRepo roomRepo;
	
	@Autowired 
	BookingRepository bookingRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired 
	PaymentRepository paymentRepo;

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
	public String bookRoom(Bookings booking) throws NotFoundException {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Users user = userRepo.findByUsername(username).get();
		Rooms room = booking.getRoom();
		Long roomId = room.getId();
		//boolean isConflict = false;
		Optional<Rooms> optRoomToBook = roomRepo.findById(roomId);		
		if (optRoomToBook.isEmpty()) {
			throw new NotFoundException();
		}
		Rooms roomToBook = optRoomToBook.get();
		List<Bookings> conflicts = bookingRepo.findConflictingBookings(
		        roomToBook.getId(),
		        booking.getCheckIn(),
		        booking.getCheckOut()
		);

		if (!conflicts.isEmpty()) {
		    return "Room is already booked for these dates";
		}
		
			booking.setUser(user);
			booking.setRoom(roomToBook);
			booking.setStatus("pending");
			long days = ChronoUnit.DAYS.between(booking.getCheckIn(), booking.getCheckOut());
			double price = days * roomToBook.getPrice();
			booking.setTotalPrice(price);
			booking.setCreatedAt(LocalDateTime.now());
			bookingRepo.save(booking);
			roomRepo.save(roomToBook);
			
			
		
		return "Room booked successfully";

	}
	public String cancelBooking(Bookings booking) throws NotFoundException {
		Long bookingId = booking.getId();
		Optional<Bookings> bookingInfo =bookingRepo.findById(bookingId);
		if(!bookingInfo.isPresent()) {
			return "No such booking details found";
		}
		Bookings bookedRoom = bookingInfo.get();
		Rooms room =bookedRoom.getRoom();
		room.setIsAvailable(true);
		roomRepo.save(room);
		bookedRoom.setStatus("canceled");
		bookingRepo.save(bookedRoom);
		
		return "Booking canceled successfully";
		
	}
	public String payment(Payments payment) throws NotFoundException {
		long bookingId = payment.getBooking().getId();
		Optional<Bookings> booking = bookingRepo.findById(bookingId);
		if(booking.isEmpty()) {
			throw new NotFoundException();
		}
		Bookings activeBooking =booking.get();
		Rooms roomToBook = activeBooking.getRoom();
		Optional<Payments>optPayment =paymentRepo.findByBookingId(bookingId);
		if(optPayment.isPresent() && "paid".equals(optPayment.get().getStatus()) && "confirmed".equals(activeBooking.getStatus())) {
			return "Room not available";
		}
		List<Bookings> conflicts = bookingRepo.findConflictingBookings(
		        roomToBook.getId(),
		        activeBooking.getCheckIn(),
		        activeBooking.getCheckOut()
		);

		if (!conflicts.isEmpty()) {
			activeBooking.setStatus("Canceled");
			bookingRepo.save(activeBooking);
		    return "Room is already booked for these dates";
		}
		
		double price = booking.get().getTotalPrice();
		if(payment.getAmount()<price) {
			payment.setStatus("failed");
			payment.setAmount(price);
			payment.setCreatedAt(LocalDateTime.now());

			return "insufficient payment amount";
			
		}
		payment.setAmount(price);
		booking.get().setStatus("confirmed");
		payment.setCreatedAt(LocalDateTime.now());
		payment.setStatus("paid");
		paymentRepo.save(payment);
		return "Payment successful";
		
	}
	public List<Rooms> searchRooms(String keyword){
		List<Rooms> result = roomRepo.searchRooms(keyword);
		 try {
			 double price = Double.parseDouble(keyword);
			 result.addAll(roomRepo.searchRoomsbyPrice(price));
		 }catch(NumberFormatException e) {
			 
	}
	try {
		int maxGuests = Integer.parseInt(keyword);
		result.addAll(roomRepo.searchRoomsbyMaxGuest(maxGuests));
	}catch(NumberFormatException e) {
		
	}
	return result;
		
	}
}
