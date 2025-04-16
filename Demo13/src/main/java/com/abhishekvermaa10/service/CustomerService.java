package com.abhishekvermaa10.service;

import com.abhishekvermaa10.dto.CompanionDTO;
import com.abhishekvermaa10.dto.CustomerDTO;
import com.abhishekvermaa10.exception.CustomerNotFoundException;

/**
 * @author abhishekvermaa10
 */
public interface CustomerService {

	Integer saveCustomer(CustomerDTO customerDTO);

	CustomerDTO findCustomer(int customerId) throws CustomerNotFoundException;

	void updateCompanionDetails(int ownerId, CompanionDTO companionDTO) throws CustomerNotFoundException;

	void deleteCustomer(int customerId) throws CustomerNotFoundException;

}
