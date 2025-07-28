package com.example.HotelBookingApp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Hotels\"")
public class Hotels {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	private String address;
	private String city;
	private double rating;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "owner_id", nullable = false) // This is the FK column in the Hotels table
	private Users owner;
	

	@OneToMany(mappedBy="hotel")
	private List<Rooms>rooms;
	
	@OneToMany(mappedBy="hotel")
	private List<Reviews>reviews;
	
	@JsonIgnore
	@OneToMany(mappedBy="hotel")
	private List<Wishlists>wishlist;

	public Hotels() {

	}

	public Hotels(long id, String name, String description, String address, String city, double rating, Users owner) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.address = address;
		this.city = city;
		this.rating = rating;
		this.owner = owner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Users getOwner() {
		return owner;
	}

	public void setOwner(Users owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Hotels [id=" + id + ", name=" + name + ", description=" + description + ", address=" + address
				+ ", city=" + city + ", rating=" + rating + ", owner=" + owner + "]";
	}

}
