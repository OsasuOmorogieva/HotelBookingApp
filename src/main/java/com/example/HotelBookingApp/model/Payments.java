package com.example.HotelBookingApp.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="\"Payments\"")
public class Payments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	@JoinColumn(name="\"booking_id\"")
	private Bookings booking;
	
	private double amount;
	@Column(name="\"payment_method\"")
	private String paymentMethod;
	private String status;
	@Column(name="\"created_at\"")
	@CurrentTimestamp
	private LocalDateTime createdAt;
	
	public Payments() {
	
	}

	public Payments(long id, Bookings booking, double amount, String paymentMethod, String status,
			LocalDateTime createdAt) {
	
		this.id = id;
		this.booking = booking;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.createdAt = createdAt;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Bookings getBooking() {
		return booking;
	}
	public void setBooking(Bookings  booking) {
		this.booking = booking;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
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
	@Override
	public String toString() {
		return "Payments [id=" + id + ", booking=" + booking + ", amount=" + amount + ", paymentMethod="
				+ paymentMethod + ", status=" + status + ", createdAt=" + createdAt + "]";
	}


}
