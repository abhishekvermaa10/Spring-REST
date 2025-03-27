package com.abhishekvermaa10.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.UpdatePetDTO;
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

	@PostMapping
	public ResponseEntity<Integer> saveOwner(@RequestBody OwnerDTO ownerDTO) {
		Integer ownerId = ownerService.saveOwner(ownerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerId);
	}

	@GetMapping("/{ownerId}")
	public ResponseEntity<OwnerDTO> findOwner(@PathVariable int ownerId) throws OwnerNotFoundException {
		OwnerDTO ownerDTO = ownerService.findOwner(ownerId);
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTO);
	}

	@PatchMapping("/{ownerId}")
	public ResponseEntity<Void> updatePetDetails(@PathVariable int ownerId, @RequestBody UpdatePetDTO updatePetDTO)
			throws OwnerNotFoundException {
		ownerService.updatePetDetails(ownerId, updatePetDTO.getName());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{ownerId}")
	public ResponseEntity<Void> deleteOwner(@PathVariable int ownerId) throws OwnerNotFoundException {
		ownerService.deleteOwner(ownerId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping("/pets/dob")
	public ResponseEntity<List<OwnerDTO>> getAllOwnersByPetDateOfBirthBetween(
			@RequestParam(defaultValue = "2010-01-01") LocalDate startDate,
			@RequestParam(defaultValue = "2014-12-01") LocalDate endDate) throws ValidationException {
		List<OwnerDTO> ownerDTOList = ownerService.findByAllOwnersByPetDateOfBirthBetween(startDate, endDate);
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTOList);
	}

}
