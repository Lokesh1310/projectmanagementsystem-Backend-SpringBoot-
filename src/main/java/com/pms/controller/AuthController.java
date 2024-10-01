package com.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.Repo.UserRepo;
import com.pms.config.JwtProvider;
import com.pms.model.Subscription;
import com.pms.model.User;
import com.pms.reponse.AuthResponse;
import com.pms.request.LoginRequest;
import com.pms.service.CustomeUserDetailsImpl;
import com.pms.service.SubscriptionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomeUserDetailsImpl customeUserDetails;

	@Autowired
	private SubscriptionService subscriptionService;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> create(@RequestBody User user) throws Exception {

		User isUserExist = userRepo.findByEmail(user.getEmail());

		if (isUserExist != null) {

			throw new Exception("email already exist with another account");
		}

		User createUser = new User();

		createUser.setPassword(passwordEncoder.encode(user.getPassword()));

		createUser.setEmail(user.getEmail());

		createUser.setFullName(user.getFullName());

		User savedUser = userRepo.save(createUser);

		subscriptionService.createSubscriprion(savedUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword());

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = JwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse();

		res.setJwt(jwt);
		res.setMessage("signUp success");
   System.out.println(jwt+"----------------------------------==");
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) {
		
		
		//TODO: process POST request
		
		String username=loginRequest.getEmail();
		
		String password=loginRequest.getPassword();
		
		Authentication authentication =authenticate(username,password);
		
	 	

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = JwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse();

		res.setJwt(jwt);
		res.setMessage("signing success");

		return new ResponseEntity<>(res, HttpStatus.CREATED);

	}

	private Authentication authenticate(String username, String password) {

UserDetails userDetails=customeUserDetails.loadUserByUsername(username);

if(userDetails==null) {
	
	throw new BadCredentialsException("invalid Username");
	
}

if(!passwordEncoder.matches(password, userDetails.getPassword())) {
	
	throw new BadCredentialsException("invalid password");

	
	
}

return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
	
	}
	

}
