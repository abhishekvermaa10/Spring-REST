package com.scaleupindia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.scaleupindia.dto.CustomerDTO;
import com.scaleupindia.dto.PetDTO;
import com.scaleupindia.exception.CustomerNotFoundException;
import com.scaleupindia.service.CustomerService;

/**
 * @author abhishekvermaa10
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private RestTemplate restTemplate;
	@Value("${pet.clinic.url}")
	private String petClinicUrl;
	@Value("${customer.not.found}")
	private String customerNotFound;

	@Override
	public void saveCustomer(CustomerDTO customerDTO) {
		restTemplate.postForLocation(petClinicUrl, customerDTO);
	}

	@Override
	public CustomerDTO findCustomer(int customerId) throws CustomerNotFoundException {
		try {
			return restTemplate.getForObject(petClinicUrl + "/" + customerId, CustomerDTO.class);
		} catch (RestClientException exception) {
			if (exception.getMessage().startsWith("404")) {
				throw new CustomerNotFoundException(customerNotFound + customerId);
			} else {
				throw exception;
			}
		}
	}

	@Override
	public void updatePetDetails(int customerId, PetDTO petDTO) throws CustomerNotFoundException {
		try {
			restTemplate.put(petClinicUrl + "/" + customerId, petDTO);
		} catch (RestClientException exception) {
			if (exception.getMessage().startsWith("404")) {
				throw new CustomerNotFoundException(customerNotFound + customerId);
			} else {
				throw exception;
			}
		}
	}

	@Override
	public void deleteCustomer(int customerId) throws CustomerNotFoundException {
		try {
			restTemplate.delete(petClinicUrl + "/" + customerId);
		} catch (RestClientException exception) {
			if (exception.getMessage().startsWith("404")) {
				throw new CustomerNotFoundException(customerNotFound + customerId);
			} else {
				throw exception;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerDTO> findAllCustomers() {
		return restTemplate.getForObject(petClinicUrl, List.class);
	}
}
