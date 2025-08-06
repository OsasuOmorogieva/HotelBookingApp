package com.example.HotelBookingApp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Rooms\"")
public class Rooms {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne()
	@JoinColumn(name="hotel_id", nullable=false)
	private Hotels hotel;
	private String type;
	private String description;
	private double price;
	@Column(name="\"max_guests\"")
	private int maxGuests;
	@Column(name="\"amenities\"")
	private String amenities;
	private Boolean isAvailable;
	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvalable) {
		this.isAvailable = isAvalable;
	}

	@JsonIgnore
	@OneToOne(mappedBy="room")
	private RoomAvailability roomAvailability;
	public Rooms() {
		
	}

	@OneToMany(mappedBy ="room")
	private List<Bookings> bookings;
	public Rooms(long id, Hotels hotel, String type, String description, double price, int maxGuests,
			String amenities,RoomAvailability roomAvailability) {
		super();
		this.id = id;
		this.hotel = hotel;
		this.type = type;
		this.description = description;
		this.price = price;
		this.maxGuests = maxGuests;
		this.amenities = amenities;
		this.roomAvailability = roomAvailability;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Hotels getHotel() {
		return hotel;
	}
	public void setHotel(Hotels hotel) {
		this.hotel = hotel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getMaxGuests() {
		return maxGuests;
	}
	public void setMaxGuests(int maxGuests) {
		this.maxGuests = maxGuests;
	}
	public RoomAvailability getRoomAvailability() {
		return roomAvailability;
	}

	public void setRoomAvailability(RoomAvailability roomAvailability) {
		this.roomAvailability = roomAvailability;
	}

	public String getAmenities() {
		return amenities;
	}
	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	@Override
	public String toString() {
		return "Rooms [id=" + id + ", hotel=" + hotel + ", type=" + type + ", description=" + description
				+ ", price=" + price + ", maxGuests=" + maxGuests + ", amenities=" + amenities + "]";
	}

	
		
	
	

}
