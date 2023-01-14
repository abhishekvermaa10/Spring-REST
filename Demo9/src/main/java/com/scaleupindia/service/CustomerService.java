package com.scaleupindia.service;

import java.util.List;

import com.scaleupindia.dto.CustomerDTO;
import com.scaleupindia.dto.PetDTO;
import com.scaleupindia.exception.CustomerNotFoundException;

/**
 * @author abhishekvermaa10
 *
 */
public interface CustomerService {
	void saveCustomer(CustomerDTO customerDTO);

	CustomerDTO findCustomer(int customerId) throws CustomerNotFoundException;

	void updatePetDetails(int customerId, PetDTO petDTO) throws CustomerNotFoundException;

	void deleteCustomer(int customerId) throws CustomerNotFoundException;

	List<CustomerDTO> findAllCustomers();
}