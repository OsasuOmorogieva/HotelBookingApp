package com.example.HotelBookingApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="\"Wishlists\"")
public class Wishlists {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	 @ManyToOne
	@JoinColumn(name="\"user_id\"")
	private Users user;
	
	@ManyToOne
	@JoinColumn(name="\"hotel_id\"")
	private Hotels hotel;
	
	
	public Wishlists() {
	
	}
	public Wishlists(long id, Users user, Hotels hotel) {
		super();
		this.id = id;
		this.user = user;
		this.hotel = hotel;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	public Hotels getHotel() {
		return hotel;
	}
	public void setHotel(Hotels hotel) {
		this.hotel = hotel;
	}
	@Override
	public String toString() {
		return "Wishlists [id=" + id + ", user=" + user + ", hotel=" + hotel + "]";
	}

}
