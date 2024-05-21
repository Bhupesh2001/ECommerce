package com.michaelakamihe.ecommercebackend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michaelakamihe.ecommercebackend.config.JwtUtil;
import com.michaelakamihe.ecommercebackend.model.User;
import com.michaelakamihe.ecommercebackend.service.JwtUserDetailsService;
import com.michaelakamihe.ecommercebackend.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/create-token")
	public ResponseEntity<?> createToken(@RequestBody Map<String, String> user) throws Exception {
		Map<String, Object> tokenResponse = new HashMap<>();
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(user.get("username"));
		final String token = jwtUtil.generateToken(userDetails);

		tokenResponse.put("token", token);
		return ResponseEntity.ok(tokenResponse);
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody Map<String, Object> user) {
		User newUser = new User((String) user.get("username"), (String) user.get("password"),
				(String) user.get("email"), (String) user.get("name"), (String) user.get("address"),
				(String) user.get("phone"));

		return new ResponseEntity<>(userService.updateUser(id, newUser), HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
