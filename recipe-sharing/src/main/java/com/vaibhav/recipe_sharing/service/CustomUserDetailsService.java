package com.vaibhav.recipe_sharing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vaibhav.recipe_sharing.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		com.vaibhav.recipe_sharing.model.User user =userRepository.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException("User not found with email"+username);
			
		}
		List<GrantedAuthority> authorities =new ArrayList<>();
		return new User(user.getEmail(),user.getPassword(),authorities);
	}

}
