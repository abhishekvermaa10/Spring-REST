package com.abhishekvermaa10.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.service.OwnerService;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@RequestMapping("/owners")
@RestController
public class OwnerController {
	
	private final OwnerService ownerService;
	
	@PostMapping
	public ResponseEntity<String> saveOwner() {
		String response = ownerService.saveOwner();
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<String> findOwner() {
		try {
			String response = ownerService.findOwner();
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (OwnerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping
	public ResponseEntity<String> updateOwner() {
		try {
			String response = ownerService.updateOwner();
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (OwnerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PatchMapping
	public ResponseEntity<String> updatePetDetails() {
		try {
			String response = ownerService.updatePetDetails();
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (OwnerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping
	public ResponseEntity<String> deleteOwner() {
		try {
			String response = ownerService.deleteOwner();
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (OwnerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping("/all")
	public ResponseEntity<String> findAllOwners() {
		String response = ownerService.findAllOwners();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
