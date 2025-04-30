package com.abhishekvermaa10.controller;

import java.time.LocalDate;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.dto.ErrorDTO;
import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.OwnerPetInfoDTO;
import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.exception.ValidationException;
import com.abhishekvermaa10.service.OwnerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@Tag(name = "Owner", description = "APIs for managing owners and their pets")
@Validated
@RequiredArgsConstructor
@RequestMapping("/owners")
@RestController
public class OwnerController {

	private final OwnerService ownerService;

	@Operation(summary = "Create owner", description = "Save a new owner and their pet to the database.")
	@ApiResponse(responseCode = "201", description = "Owner Successfully Created")
	@ApiResponse(responseCode = "400", description = "Constraint Violation Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@PostMapping
	public ResponseEntity<Integer> saveOwner(@Valid @RequestBody OwnerDTO ownerDTO) {
		Integer ownerId = ownerService.saveOwner(ownerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(ownerId);
	}

	@Operation(summary = "Get owner by owner ID", description = "Retrieve owner details using their unique id.")
	@ApiResponse(responseCode = "200", description = "Owner Details Retrieved Successfully")
	@ApiResponse(responseCode = "400", description = "Constraint Violation Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "404", description = "Owner Not Found", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@GetMapping("/{ownerId}")
	public ResponseEntity<OwnerDTO> findOwner(
			@Parameter(description = "Owner id to fetch the owner, must be a positive number.")
			@PathVariable @Min(value = 1, message = "{owner.id.positive}") int ownerId) throws OwnerNotFoundException {
		OwnerDTO ownerDTO = ownerService.findOwner(ownerId);
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTO);
	}

	@Operation(summary = "Filter owners by pet's date of birth", description = "Retrieve owners whose pets were born within a specific date range.")
	@ApiResponse(responseCode = "200", description = "List of Owners Retrieved Successfully.")
	@ApiResponse(responseCode = "400", description = "Constraint Violation Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "409", description = "Validation Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@GetMapping("/pets/dob")
	public ResponseEntity<List<OwnerDTO>> getAllOwnersByPetDateOfBirthBetween(
			@Parameter(description = "Starting range of pet's date of birth, must be in past or present.")
			@RequestParam(defaultValue = "2010-01-01") @PastOrPresent(message = "{startDate.old}") LocalDate startDate,
			@Parameter(description = "Ending range of pet's date of birth, must be in past or present.")
			@RequestParam(defaultValue = "2014-12-01") @PastOrPresent(message = "{endDate.old}") LocalDate endDate)
			throws ValidationException {
		List<OwnerDTO> ownerDTOList = ownerService.findByAllOwnersByPetDateOfBirthBetween(startDate, endDate);
		return ResponseEntity.status(HttpStatus.OK).body(ownerDTOList);
	}

	@Operation(summary = "Get owner details", description = "Retrieve details of owners including their IDs, first and last names, and pet names.")
	@ApiResponse(responseCode = "200", description = "Owner Details Retrieved Successfully")
	@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
	@GetMapping("/details")
	public ResponseEntity<Page<OwnerPetInfoDTO>> getOwnerDetails(@ParameterObject Pageable pageable) {
		Page<OwnerPetInfoDTO> ownerDetailsList = ownerService.findOwnerDetails(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(ownerDetailsList);
	}

}
