package com.scaleupindia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scaleupindia.dto.CustomerDTO;
import com.scaleupindia.dto.PetDTO;
import com.scaleupindia.exception.CustomerNotFoundException;
import com.scaleupindia.service.CustomerService;

/**
 * @author abhishekvermaa10
 *
 */
@RequestMapping("/customers")
@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<Void> createCustomer(@RequestBody CustomerDTO customerDTO) {
		customerService.saveCustomer(customerDTO);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable int customerId) throws CustomerNotFoundException {
		CustomerDTO customerDTO = customerService.findCustomer(customerId);
		return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<Void> updatePetDetails(@PathVariable int customerId, @RequestBody PetDTO petDTO)
			throws CustomerNotFoundException {
		customerService.updatePetDetails(customerId, petDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId) throws CustomerNotFoundException {
		customerService.deleteCustomer(customerId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@GetMapping
	public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
		List<CustomerDTO> customerDTOList = customerService.findAllCustomers();
		return ResponseEntity.status(HttpStatus.OK).body(customerDTOList);
	}
}
