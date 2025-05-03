package com.abhishekvermaa10.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.service.OwnerService;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@Validated
@RequiredArgsConstructor
@RequestMapping("/owners")
@RestController
public class OwnerController {

	private final OwnerService ownerService;

	@GetMapping("/{ownerId}")
	public ResponseEntity<OwnerDTO> findOwner(
			@PathVariable @Min(value = 1, message = "{owner.id.positive}") int ownerId) throws OwnerNotFoundException {
		OwnerDTO ownerDTO = ownerService.findOwner(ownerId);
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTO);
	}

}
