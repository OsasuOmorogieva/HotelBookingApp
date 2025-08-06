package com.example.HotelBookingApp.DTO;

public class HotelDTO {
	    private String name;
	    private String description;
	    private String address;
	    private String city;
	    private double rating;

	    public HotelDTO(String name, String description, String address, String city, double rating) {
	      
	        this.name = name;
	        this.description = description;
	        this.address = address;
	        this.city = city;
	        this.rating = rating;
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

}
