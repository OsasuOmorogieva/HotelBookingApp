package com.example.HotelBookingApp.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CurrentTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="\"Reviews\"")
public class Reviews {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="\"user_id\"")
	private Users user;
	
	@ManyToOne
	@JoinColumn(name="\"hotel_id\"")
	private Hotels hotel;
	private double rating;
	private String comment;
	@Column(name="\"created_at\"")
	@CurrentTimestamp
	private LocalDateTime createdAt;
	
	
	public Reviews() {
		
	}
	public Reviews(long id, Users user, Hotels hotel, double rating, String comment, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.user = user;
		this.hotel = hotel;
		this.rating = rating;
		this.comment = comment;
		this.createdAt = createdAt;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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
		return "Reviews [id=" + id + ", user=" + user + ", hotel=" + hotel + ", rating=" + rating + ", comment="
				+ comment + ", createdAt=" + createdAt + "]";
	}
	
}
