package com.abhishekvermaa10.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.dto.OwnerDTO;
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

	@GetMapping
	public ResponseEntity<List<OwnerDTO>> findAllOwners() {
		List<OwnerDTO> ownerDTOList = ownerService.findAllOwners();
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTOList);
	}
	
	@GetMapping(params = "version=2")
	public ResponseEntity<List<OwnerDTO>> findAllOwnersWithoutPets() {
		List<OwnerDTO> ownerDTOList = ownerService.findAllOwnersWithoutPets();
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTOList);
	}

}
