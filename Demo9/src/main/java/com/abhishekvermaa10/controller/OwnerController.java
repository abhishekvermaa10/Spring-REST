package com.abhishekvermaa10.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.OwnerPetInfoDTO;
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

	@GetMapping("/details")
	public ResponseEntity<List<OwnerPetInfoDTO>> getOwnerDetails(
			@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "1") int pageSize, 
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "false") boolean descending) {
		List<OwnerPetInfoDTO> ownerPetInfoDTOList = ownerService.findOwnerDetails(pageNumber, pageSize, sortBy, descending);
		return ResponseEntity.status(HttpStatus.OK).body(ownerPetInfoDTOList);
	}
	
	@GetMapping("/details/list")
	public ResponseEntity<List<OwnerPetInfoDTO>> getOwnerDetailsAsList(
			Pageable pageable) {
		List<OwnerPetInfoDTO> ownerPetInfoDTOList = ownerService.findOwnerDetailsAsList(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(ownerPetInfoDTOList);
	}
	
	@GetMapping("/details/page")
	public ResponseEntity<Page<OwnerPetInfoDTO>> getOwnerDetailsAsPage(
			Pageable pageable) {
		Page<OwnerPetInfoDTO> ownerPetInfoDTOPage = ownerService.findOwnerDetailsAsPage(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(ownerPetInfoDTOPage);
	}

}
