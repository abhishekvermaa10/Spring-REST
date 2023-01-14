package com.scaleupindia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scaleupindia.service.OwnerService;

/**
 * @author abhishekvermaa10
 *
 */
@RequestMapping("/owners")
@RestController
public class OwnerController {
	@Autowired
	private OwnerService ownerService;

	@PostMapping
	public String createOwner() {
		return ownerService.saveOwner();
	}

	@GetMapping
	public String getOwner() {
		return ownerService.findOwner();
	}

	@PutMapping
	public String updatePetDetails() {
		return ownerService.updatePetDetails();
	}

	@DeleteMapping
	public String deleteOwner() {
		return ownerService.deleteOwner();
	}

	@GetMapping("/all")
	public String getAllOwners() {
		return ownerService.findAllOwners();
	}
}
