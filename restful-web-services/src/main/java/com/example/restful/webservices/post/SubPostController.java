package com.example.restful.webservices.post;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubPostController {
	
	@Autowired
	private SubPostRepository subpostRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	@GetMapping("/posts")
	public List<SubPost> retrievePost() {
		return subpostRepository.findAll(); 
	}
	
	@PostMapping("/posts")
	public void createPost(@RequestBody SubPost subpost) {
		subpostRepository.save(subpost);
	}
	
	@GetMapping("/tags")
	public List<Tag> retrieveTag() {
		return tagRepository.findAll(); 
	}
	
	@PostMapping("/tags")
	public void createTag(@RequestBody Tag tag) {
		tagRepository.save(tag);
	}
	
	
	
}
