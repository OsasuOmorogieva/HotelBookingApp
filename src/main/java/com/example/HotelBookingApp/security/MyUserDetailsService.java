package com.example.HotelBookingApp.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.HotelBookingApp.Repository.UserRepository;
import com.example.HotelBookingApp.exception.UserNotFoundException;
import com.example.HotelBookingApp.model.Users;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> user = userRepo.findByUsername(username);
		if(user == null) {
			System.out.println("User Not Found");
			throw new UserNotFoundException("User not found");
		}
		return new MyUserDetails(user);
	}
	

}
