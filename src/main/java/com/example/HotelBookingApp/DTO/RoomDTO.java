package com.example.HotelBookingApp.DTO;

public class RoomDTO {
	private Long id;
	private HotelDTO hotel;
	private String type;
	private String description;
	private double price;
	private int maxguests;
	private String amenities;
	private Boolean isAvailable;
	
	public RoomDTO(Long id, HotelDTO hotel, String type, String description, double price, int maxguests,
			String amenities, Boolean isAvailable) {
		super();
		this.id = id;
		this.type = type;
		this.description = description;
		this.price = price;
		this.maxguests = maxguests;
		this.amenities = amenities;
		this.isAvailable = isAvailable;
		this.hotel = hotel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getMaxguests() {
		return maxguests;
	}

	public void setMaxguests(int maxguests) {
		this.maxguests = maxguests;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}
	

	public HotelDTO getHotel() {
		return hotel;
	}

	public void setHotel(HotelDTO hotel) {
		this.hotel = hotel;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
}
