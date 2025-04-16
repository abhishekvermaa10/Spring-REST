package com.abhishekvermaa10.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.dto.CompanionDTO;
import com.abhishekvermaa10.dto.CustomerDTO;
import com.abhishekvermaa10.exception.CustomerNotFoundException;
import com.abhishekvermaa10.service.CustomerService;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@RequestMapping("/customers")
@RestController
public class CustomerController {

	private final CustomerService customerService;

	@PostMapping
	public ResponseEntity<Integer> saveCustomer(@RequestBody CustomerDTO customerDTO) {
		Integer customerId = customerService.saveCustomer(customerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(customerId);
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDTO> findCustomer(@PathVariable int customerId) throws CustomerNotFoundException {
		CustomerDTO customerDTO = customerService.findCustomer(customerId);
		return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
	}

	@PatchMapping("/{customerId}")
	public ResponseEntity<Void> updateCompanionDetails(@PathVariable int customerId,
			@RequestBody CompanionDTO companionDTO) throws CustomerNotFoundException {
		customerService.updateCompanionDetails(customerId, companionDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId) throws CustomerNotFoundException {
		customerService.deleteCustomer(customerId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
