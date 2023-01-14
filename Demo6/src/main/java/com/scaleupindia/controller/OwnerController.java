package com.scaleupindia.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.dto.PetDTO;
import com.scaleupindia.exception.OwnerNotFoundException;
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
	public ResponseEntity<Void> createOwner(@RequestBody OwnerDTO ownerDTO) {
		ownerService.saveOwner(ownerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{ownerId}")
	public ResponseEntity<OwnerDTO> getOwner(@PathVariable int ownerId) {
		try {
			OwnerDTO ownerDTO = ownerService.findOwner(ownerId);
			return ResponseEntity.status(HttpStatus.OK).body(ownerDTO);
		} catch (OwnerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PutMapping("/{ownerId}")
	public ResponseEntity<Void> updatePetDetails(@PathVariable int ownerId, @RequestBody PetDTO petDTO) {
		try {
			ownerService.updatePetDetails(ownerId, petDTO.getName());
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (OwnerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("/{ownerId}")
	public ResponseEntity<Void> deleteOwner(@PathVariable int ownerId) {
		try {
			ownerService.deleteOwner(ownerId);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (OwnerNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping
	public ResponseEntity<List<OwnerDTO>> getAllOwners() {
		List<OwnerDTO> ownerDTOList = ownerService.findAllOwners();
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTOList);
	}

	@GetMapping("/filterByDate")
	public ResponseEntity<List<OwnerDTO>> getAllOwnersByPetDateOfBirthBetween(
			@RequestParam(defaultValue = "2022-12-01") LocalDate startDate,
			@RequestParam(defaultValue = "2022-12-31") LocalDate endDate) {
		List<OwnerDTO> ownerDTOList = ownerService.findByAllOwnersByPetDateOfBirthBetween(startDate, endDate);
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTOList);
	}

	@GetMapping("/filterByLocation/{location}")
	public ResponseEntity<List<OwnerDTO>> getAllOwnersByCityAndState(@MatrixVariable Map<String, String> location) {
		List<OwnerDTO> ownerDTOList = ownerService.findAllOwnersByCityAndState(location.get("city"),
				location.get("state"));
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTOList);
	}
}
