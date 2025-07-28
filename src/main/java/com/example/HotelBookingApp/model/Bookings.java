package com.example.HotelBookingApp.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Bookings\"")
public class Bookings {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="\"user_id\"")
	private Users user;
	
	@ManyToOne
	@JoinColumn(name="\"room_id\"")
	private Rooms room;
	
	@OneToOne(mappedBy="booking")
	private Payments payment;
	@Column(name="\"check_in\"")
	private Date checkIn;
	
	@Column(name="\"check_out\"")
	private Date checkOut;
	@Column(name="\"total_price\"")
	private double totalPrice;
	private String status;
	@Column(name="\"created_at\"")
	@CurrentTimestamp
	private LocalDateTime createdAt;
	
	
	
	public Bookings() {
	}
	public Bookings(long id, Users user, Rooms room, Date checkIn, Date checkOut, double totalPrice, String status,
			LocalDateTime createdAt) {
		super();
		this.id = id;
		this.user = user;
		this.room = room;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.totalPrice = totalPrice;
		this.status = status;
		this.createdAt = createdAt;
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
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		
	}
	public Rooms getRoom() {
		return room;
	}
	public void setRoom(Rooms room) {
		this.room = room;
	}
	@Override
	public String toString() {
		return "Bookings [id=" + id + ", user=" + user + ", room=" + room + ", checkIn=" + checkIn + ", checkOut="
				+ checkOut + ", totalPrice=" + totalPrice + ", status=" + status + ", createdAt=" + createdAt + "]";
	}
	
	

}
