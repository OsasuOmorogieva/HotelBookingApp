package com.example.HotelBookingApp.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"RoomAvailability\"")
public class RoomAvailability {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name="\"room_id\"")
	private Rooms room;
	private Date checkIn;
	private Date checkOut;
	
	private Date date;
	@Column(name="\"is_available\"")
	private boolean isAvailable;
	private double price;
	
	
	public RoomAvailability() {
	
	}
	public RoomAvailability(long id, Rooms room, Date date, boolean isAvailable, double price) {
		super();
		this.id = id;
		this.room = room;
		this.date = date;
		this.isAvailable = isAvailable;
		this.price = price;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Rooms getRoom() {
		return room;
	}
	public void setRoom(Rooms room) {
		this.room = room;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean getIsAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	@Override
	public String toString() {
		return "RoomAvailability [id=" + id + ", room=" + room + ", date=" + date + ", isAvailable=" + isAvailable
				+ ", price=" + price + "]";
	}
	
	

}
