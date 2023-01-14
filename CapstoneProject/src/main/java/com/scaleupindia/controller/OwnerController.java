package com.scaleupindia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

/**
 * @author abhishekvermaa10
 *
 */
@Validated
@RequestMapping("/owners")
@RestController
public class OwnerController {
	@Autowired
	private OwnerService ownerService;

	@PostMapping
	public ResponseEntity<Void> createOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
		ownerService.saveOwner(ownerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{ownerId}")
	public ResponseEntity<OwnerDTO> getOwner(@PathVariable @Min(value = 1, message = "{owner.id.positive}") int ownerId)
			throws OwnerNotFoundException {
		OwnerDTO ownerDTO = ownerService.findOwner(ownerId);
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTO);
	}

	@PutMapping("/{ownerId}")
	public ResponseEntity<Void> updatePetDetails(
			@PathVariable @Min(value = 1, message = "{owner.id.positive}") int ownerId, @RequestBody PetDTO petDTO)
			throws OwnerNotFoundException {
		ownerService.updatePetDetails(ownerId, petDTO.getName());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{ownerId}")
	public ResponseEntity<Void> deleteOwner(@PathVariable @Min(value = 1, message = "{owner.id.positive}") int ownerId)
			throws OwnerNotFoundException {
		ownerService.deleteOwner(ownerId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping
	public ResponseEntity<List<OwnerDTO>> getAllOwners(@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "50") int itemsPerPage,
			@RequestParam(defaultValue = "id") String sortingParameter,
			@RequestParam(defaultValue = "false") boolean sortDesc) {
		List<OwnerDTO> ownerDTOList = ownerService.findAllOwners(pageNumber, itemsPerPage, sortingParameter, sortDesc);
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTOList);
	}
}
