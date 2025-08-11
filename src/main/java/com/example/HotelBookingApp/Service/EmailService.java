package com.example.HotelBookingApp.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.HotelBookingApp.model.Users;
import com.example.HotelBookingApp.provider.ResourceProvider;
import com.example.HotelBookingApp.security.JWTService;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${spring.mail.username}")
	private String emailFrom;
	@Autowired 
	ResourceProvider provider;
	
	@Autowired
	TemplateEngine templateEngine;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	JWTService jwtService;

	@Async
	public void sendVerificationEmail(Users user) {
		this.sendEmail(user, this.provider
				.getClientVerifyParam(),"Verify_email",
				String.format("Welcome %s %s", user.getFirstName(), 
				user.getLastName()), 
				this.provider.getClientVerifyExpiration());
		
	}

	@Async
	public void sendResetPasswordEmail(Users users) {
		this.sendEmail(users, this.provider.getClientResetParam(), "reset_password", "Reset your password", this.provider.getClientResetExpiration());
		
	}
	private void sendEmail(Users user, String clientParam, String templateName, String emailSubject,
			long expiration) {
		try {
		Context context = new Context();
		context.setVariable("user", user);
		context.setVariable("client", this.provider.getClientUrl());
		context.setVariable("param", clientParam);
		context.setVariable("token", this.jwtService.generateToken(user.getUsername()));
		String process = this.templateEngine.process(templateName, context);
		MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		
		helper.setFrom(this.emailFrom, "Easy Booking");
		helper.setSubject(emailSubject);
		helper.setText(process, true);
		helper.setTo(user.getEmail());
		
		this.javaMailSender.send(mimeMessage);
		this.logger.debug("Email sent, {}", user.getEmail());
	}catch(Exception ex) {
		this.logger.error("Error while sending email, username: "+ user.getUsername(), ex) ;
	}
	

}
}
