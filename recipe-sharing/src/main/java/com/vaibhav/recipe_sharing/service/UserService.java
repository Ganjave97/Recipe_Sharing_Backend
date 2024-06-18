package com.vaibhav.recipe_sharing.service;

import com.vaibhav.recipe_sharing.model.User;

public interface UserService {
	public User findUserById(Long userId) throws Exception;
	
	public User findUserByJwt(String jwt) throws Exception;
	

}
