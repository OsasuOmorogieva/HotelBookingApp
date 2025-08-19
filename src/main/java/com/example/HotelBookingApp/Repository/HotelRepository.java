package com.example.HotelBookingApp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.HotelBookingApp.model.Hotels;

public interface HotelRepository extends JpaRepository<Hotels, Long> {
	Optional<Hotels> findByOwnerId(Long ownerId);

	Optional<Hotels> findByNameIgnoreCaseAndAddressIgnoreCaseAndCityIgnoreCase(String name, String address, String city);
	Optional<Hotels> findByNameIgnoreCaseAndCityIgnoreCase(String name, String city);

	
	
	@Query("SELECT h FROM Hotels h WHERE "+
			"LOWER(h.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
			+ "LOWER(h.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
			+ "LOWER(h.address) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
			+ "LOWER(h.city) LIKE LOWER(CONCAT('%', :keyword, '%')) ")
	List<Hotels> search(@Param("keyword")String keyword);
	
	@Query("SELECT h FROM Hotels h WHERE h.rating = :double rating")
	List<Hotels> searchByRating(@Param("rating") double rating);

}
