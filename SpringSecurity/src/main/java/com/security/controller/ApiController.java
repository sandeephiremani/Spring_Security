package com.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("welcome")
public class ApiController {

	@PreAuthorize("hasRole('NORMAL')")
	@GetMapping("/normal")
	public ResponseEntity<?> normalUser() {
		return ResponseEntity.ok("I'm Normal User");
	}
	
	
	@GetMapping("/public")
	public ResponseEntity<?> publicUser() {
		return ResponseEntity.ok("I'm public User");
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public ResponseEntity<?> AdminUser() {
		return ResponseEntity.ok("I'm Admin");
	}
}
