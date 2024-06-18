package com.vaibhav.recipe_sharing.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping
	public String homecontroller() {
		return "Welcome back to vaibhavs content";
	}
	
	//@PostMapping
	

}
