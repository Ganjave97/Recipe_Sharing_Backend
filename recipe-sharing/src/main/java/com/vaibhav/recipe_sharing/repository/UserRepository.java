package com.vaibhav.recipe_sharing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaibhav.recipe_sharing.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);

}
