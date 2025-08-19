package com.example.HotelBookingApp.DTO;

import java.time.LocalDate;

import com.example.HotelBookingApp.model.Hotels;
import com.fasterxml.jackson.annotation.JsonFormat;

public class DateRange {
	private Hotels hotel;
	
	public Hotels getHotel() {
		return hotel;
	}

	public void setHotel(Hotels hotel) {
		this.hotel = hotel;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkIn;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOut;

    // Getters and setters
    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

}
