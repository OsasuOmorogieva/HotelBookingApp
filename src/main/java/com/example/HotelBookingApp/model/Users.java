package com.example.HotelBookingApp.model;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Users\"")
public class Users {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String phone;
	private Boolean emailVerified;
	private Timestamp createdOn;
	private String password;
	private String role;
	public Users() {
		
	}
	
	
	public Users(Long id, String firstName, String lastName, String username, String email, String phone,
			Boolean emailVerified, Timestamp createdOn, String password, String role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.emailVerified = emailVerified;
		this.createdOn = createdOn;
		this.password = password;
		this.role = role;
	}



	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Boolean getEmailVerified() {
		return emailVerified;
	}


	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}


	public Timestamp getCreatedOn() {
		return createdOn;
	}


	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	@JsonIgnore
	@OneToMany(mappedBy="owner")
	private List<Hotels> hotels;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Bookings>bookings;
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Reviews> reviews;
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Wishlists> wishlist;
	@Override
	public String toString() {
		return "Users [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", email=" + email + ", phone=" + phone + ", emailVerified=" + emailVerified + ", createdOn="
				+ createdOn + ", password=" + password + ", role=" + role + "]";
	}

	
}
