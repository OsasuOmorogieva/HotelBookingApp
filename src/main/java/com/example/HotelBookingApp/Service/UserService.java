package com.example.HotelBookingApp.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.HotelBookingApp.Repository.HotelRepository;
import com.example.HotelBookingApp.Repository.RoomsRepo;
import com.example.HotelBookingApp.Repository.UserRepository;
import com.example.HotelBookingApp.Repository.WishlistsRepository;
import com.example.HotelBookingApp.exception.EmailExistException;
import com.example.HotelBookingApp.exception.UserExistException;
import com.example.HotelBookingApp.exception.UserNotFoundException;
import com.example.HotelBookingApp.model.Users;
import com.example.HotelBookingApp.model.Wishlists;
import com.example.HotelBookingApp.security.JWTService;

@Service
public class UserService {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

	@Autowired
	UserRepository userRepo;

	@Autowired
	AuthenticationManager authManager;


	@Autowired
	RoomsRepo roomRepo;
	
	@Autowired
	WishlistsRepository wishlistRepo;
	
	@Autowired
	HotelRepository hotelRepo;
	
	@Autowired
	JWTService jwtService;
	
	@Autowired
	EmailService emailService;
	


	public Users register(Users user) {
		user.setUsername(user.getUsername().toLowerCase());
		user.setEmail(user.getEmail());
		this.validateUsernameAndEmail(user.getUsername(), user.getEmail());
		user.setCreatedOn(Timestamp.from(Instant.now()));
		user.setFirstName(user.getFirstName().toLowerCase());
		user.setLastName(user.getLastName().toLowerCase());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setPhone(user.getPhone());
		user.setEmailVerified(false);
		userRepo.save(user);
		this.emailService.sendVerificationEmail(user);
		return user;
	}
	
	public String login(Users user) {
		Authentication authentication = authManager.authenticate
				(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getUsername());
		}
		return "unable to authenticate user.";
	}

	private void validateUsernameAndEmail(String username, String email) {
		this.userRepo.findByUsername(username)
		.ifPresent(u->{
			throw new UserExistException(String.format("Username already exists", u.getUsername()));
			});
		
		this.userRepo.findByEmail(email)
		.ifPresent(u->{
			throw new EmailExistException(String.format("Email already exists", u.getEmail()));
		});
		
	}
	public void verifyEmail() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Users user = this.userRepo.findByUsername(username)
				.orElseThrow(() -> new UserNotFoundException(String.format("username doesn't exist", username)));
		user.setEmailVerified(true);
		userRepo.save(user);
	}
	
	public void resetPassword(String password) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Users user = userRepo.findByUsername(username).orElseThrow(()->new UserNotFoundException(String.format("username does not exist", username)));
		user.setPassword(password);
		userRepo.save(user);
	}

	public void sendResetPasswordEmail(String email) {
		Users user = userRepo.findByEmail(email).orElseThrow(()-> new UserNotFoundException(String.format("user does not exist", email)));
		this.emailService.sendResetPasswordEmail(user);
		logger.debug("Email doesn't exist{}", email);
	}
	
	public List<Wishlists> getWishlist(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Users user = userRepo.findByUsername(username).orElseThrow(()->new UserNotFoundException(String.format("username does not exist", username)));
		Long userId = user.getId();
		List<Wishlists> wishlists = wishlistRepo.findByUserId(userId);
		return wishlists;
	}
	
	
	
}
