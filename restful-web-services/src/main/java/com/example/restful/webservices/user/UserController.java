package com.example.restful.webservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private PostRepository postrepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers(){
		return userrepository.findAll();
	}
	
	@GetMapping("jpa/user/{id}")
	public Optional<User> retrieveUser(@PathVariable int id){
		Optional<User> user=userrepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		return user;
	}
	
	@DeleteMapping("jpa/user/{id}")
	public void deleteUser(@PathVariable int id) {
		userrepository.deleteById(id);
	}
	
	@PostMapping("jpa/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser=userrepository.save(user);
		
		URI location=ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("jpa/user/{id}/posts")
	public List<Post> retrieveUserPosts(@PathVariable int id){
		Optional<User> user=userrepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		return user.get().getPost();
	}
	
	@PostMapping("jpa/user/{id}/post")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> userOptional=userrepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		User user=userOptional.get();
		post.setUser(user);
		postrepository.save(post);
		
		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
}
