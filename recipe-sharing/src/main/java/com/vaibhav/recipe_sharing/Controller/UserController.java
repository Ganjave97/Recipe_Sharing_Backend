package com.vaibhav.recipe_sharing.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhav.recipe_sharing.model.User;
import com.vaibhav.recipe_sharing.repository.UserRepository;
import com.vaibhav.recipe_sharing.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/api/users/profile")
	public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
		User user=userService.findUserByJwt(jwt);
		return user;
	}
	
	/*
	 * @Autowired private UserRepository userRepository;
	 */
	
	/*
	 * @PostMapping("/users") public User createUser(@RequestBody User user) throws
	 * Exception{ User isExist=userRepository.findByEmail(user.getEmail()); if
	 * (isExist != null) { throw new
	 * Exception("User is exist with "+user.getEmail()); } User savedUser =
	 * userRepository.save(user);
	 * 
	 * return savedUser; }
	 */
	
	/*
	 * @DeleteMapping("/users/{userId}") public String deleteUser(@PathVariable Long
	 * userId) throws Exception{
	 * 
	 * userRepository.deleteById(userId);
	 * 
	 * return "User is deleted successfully"; }
	 */
	
	/*
	 * @GetMapping("/users") public List<User> getAllUsers() throws Exception{
	 * List<User> users=userRepository.findAll(); return users; }
	 */
	
	
	
	/*
	 * public User findbyEmail(String email) { User user =
	 * userRepository.findByEmail(email); if (user == null) { throw new
	 * Exception("User not found with email" + email); } return User; }
	 */

}
