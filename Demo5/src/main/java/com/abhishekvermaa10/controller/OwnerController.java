package com.abhishekvermaa10.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.exception.ValidationException;
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

	@GetMapping("/{ownerId}")
	public ResponseEntity<?> findOwner(@PathVariable int ownerId) {
		try {
			OwnerDTO ownerDTO = ownerService.findOwner(ownerId);
			return ResponseEntity.status(HttpStatus.OK).body(ownerDTO);
		} catch (OwnerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping("/pets/dob")
	public ResponseEntity<?> getAllOwnersByPetDateOfBirthBetween(
			@RequestParam(defaultValue = "2010-01-01") LocalDate startDate,
			@RequestParam(defaultValue = "2014-12-01") LocalDate endDate) {
		try {
			List<OwnerDTO> ownerDTOList = ownerService.findByAllOwnersByPetDateOfBirthBetween(startDate, endDate);
			return ResponseEntity.status(HttpStatus.OK).body(ownerDTOList);
		} catch (ValidationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

}
