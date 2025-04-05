package com.abhishekvermaa10.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.OwnerPetInfoDTO;
import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.exception.ValidationException;
import com.abhishekvermaa10.service.OwnerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
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

	@PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Integer> saveOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
		Integer ownerId = ownerService.saveOwner(ownerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerId);
	}

	@GetMapping(value = "/{ownerId}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<OwnerDTO> findOwner(
			@PathVariable @Min(value = 1, message = "{owner.id.positive}") int ownerId) throws OwnerNotFoundException {
		OwnerDTO ownerDTO = ownerService.findOwner(ownerId);
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTO);
	}

	@GetMapping(value = "/pets/dob", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<OwnerDTO>> getAllOwnersByPetDateOfBirthBetween(
			@RequestParam(defaultValue = "2010-01-01") @PastOrPresent(message = "{startDate.old}") LocalDate startDate,
			@RequestParam(defaultValue = "2014-12-01") @PastOrPresent(message = "{endDate.old}") LocalDate endDate)
			throws ValidationException {
		List<OwnerDTO> ownerDTOList = ownerService.findByAllOwnersByPetDateOfBirthBetween(startDate, endDate);
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTOList);
	}

	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<OwnerDTO>> findAllOwners() {
		List<OwnerDTO> ownerDTOList = ownerService.findAllOwners();
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTOList);
	}

	@GetMapping(value = "/details", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Page<OwnerPetInfoDTO>> getOwnerDetails(Pageable pageable) {
		Page<OwnerPetInfoDTO> ownerPetInfoDTOPage = ownerService.findOwnerDetails(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(ownerPetInfoDTOPage);
	}

}
