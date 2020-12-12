package com.example.restful.webservices.onetoone;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OneUserController {
	
	@Autowired
	private OneUserRepository oneuserRepository;
	
	@Autowired
	private UserProfileRepository upRepository;
	
	@GetMapping("/users")
	public List<OneUser> retrieveUser(){
		return oneuserRepository.findAll(); 
	}
	
	@PostMapping("/users")
	public void createUser(@RequestBody OneUser user) {
		oneuserRepository.save(user);	
	}
	
	@PostMapping("/userprofile")
	public void createUserProfile(@RequestBody UserProfile userProfile) {
		upRepository.save(userProfile);
	}
	@GetMapping("/userprofile")
	public List<UserProfile> retrieveUserProfile(){
		return upRepository.findAll(); 
	}
	
	
}
