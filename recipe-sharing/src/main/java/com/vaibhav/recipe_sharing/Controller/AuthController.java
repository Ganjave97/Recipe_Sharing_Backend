package com.vaibhav.recipe_sharing.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhav.recipe_sharing.config.JwtProvider;
import com.vaibhav.recipe_sharing.model.User;
import com.vaibhav.recipe_sharing.repository.UserRepository;
import com.vaibhav.recipe_sharing.request.LoginRequest;
import com.vaibhav.recipe_sharing.response.AuthResponse;
import com.vaibhav.recipe_sharing.service.CustomUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception{
		
		String email=user.getEmail();
		String password=user.getPassword();
		String fullName=user.getFullname();
		// Check if email is already in use
		User existingUser =userRepository.findByEmail(email);
		if(existingUser !=null) {
			throw new Exception("Email is use with another account");
		}
		// Create new user
		User createdUser=new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFullname(fullName);
		// Save the user to the database
		userRepository.save(createdUser);
		// Authenticate the user
		Authentication authentication=new UsernamePasswordAuthenticationToken(email, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// Generate JWT token
		String token =jwtProvider.generateToken(authentication);
		// Create and populate AuthResponse
		AuthResponse res=new AuthResponse();
		
		res.setJwt(token);
		res.setMessage("Sign Up is sucess");
		return res;
	}
	@PostMapping("/signin")
	public AuthResponse signinHandler(@RequestBody LoginRequest loginRequest) {
		String username=loginRequest.getEmail();
		String password=loginRequest.getPassword();
		
		Authentication authentication=authentication(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse();

		res.setJwt(token);
		res.setMessage("Sign in is sucess");
		return res;
	}

	private Authentication authentication(String username, String password) {
		// TODO Auto-generated method stub
		UserDetails userDetails=customUserDetailsService.loadUserByUsername(username);
		
		if(userDetails==null) {
			throw new  BadCredentialsException("User not Found");
			
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}
	
		return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
	}

}
