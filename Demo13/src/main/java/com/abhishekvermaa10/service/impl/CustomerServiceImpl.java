package com.abhishekvermaa10.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.abhishekvermaa10.dto.CompanionDTO;
import com.abhishekvermaa10.dto.CustomerDTO;
import com.abhishekvermaa10.exception.CustomerNotFoundException;
import com.abhishekvermaa10.service.CustomerService;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

	private final RestTemplate restTemplate;
	@Value("${target.base.url}")
	private String targetBaseUrl;
	@Value("${customer.not.found}")
	private String customerNotFound;

	public Integer saveCustomerUsingObject(CustomerDTO customerDTO) {
		try {
			Integer response = restTemplate.postForObject(targetBaseUrl, customerDTO, Integer.class);
			return response;
		} catch (RestClientException exception) {
			handleException(exception);
			return null;
		}
	}
	
	public Integer saveCustomerUsingEntity(CustomerDTO customerDTO) {
		try {
			ResponseEntity<Integer> response = restTemplate.postForEntity(targetBaseUrl, customerDTO, Integer.class);
			return response.getBody();
		} catch (RestClientException exception) {
			handleException(exception);
			return null;
		}
	}
	
	@Override
	public Integer saveCustomer(CustomerDTO customerDTO) {
		try {
			HttpEntity<CustomerDTO> requestEntity = new HttpEntity<>(customerDTO);
			ResponseEntity<Integer> response = restTemplate.exchange(targetBaseUrl, HttpMethod.POST, requestEntity,
					Integer.class);
			return response.getBody();
		} catch (RestClientException exception) {
			handleException(exception);
			return null;
		}
	}
	
	public CustomerDTO findCustomerUsingObject(int customerId) throws CustomerNotFoundException {
		try {
			CustomerDTO response = restTemplate.getForObject(targetBaseUrl + "/" + customerId, CustomerDTO.class);
			return response;
		} catch (RestClientException exception) {
			handleNotFoundException(exception, customerId);
			return null;
		}
	}
	
	public CustomerDTO findCustomerUsingEntity(int customerId) throws CustomerNotFoundException {
		try {
			ResponseEntity<CustomerDTO> responseEntity = restTemplate.getForEntity(targetBaseUrl + "/" + customerId,
					CustomerDTO.class);
			return responseEntity.getBody();
		} catch (RestClientException exception) {
			handleNotFoundException(exception, customerId);
			return null;
		}
	}

	@Override
	public CustomerDTO findCustomer(int customerId) throws CustomerNotFoundException {
		try {
			ResponseEntity<CustomerDTO> responseEntity = restTemplate.exchange(targetBaseUrl + "/" + customerId,
					HttpMethod.GET, HttpEntity.EMPTY, CustomerDTO.class);
			return responseEntity.getBody();
		} catch (RestClientException exception) {
			handleNotFoundException(exception, customerId);
			return null;
		}
	}
	
	@SuppressWarnings("unused")
	public void updateCompanionDetailsUsingObject(int customerId, CompanionDTO companionDTO) throws CustomerNotFoundException {
		try {
			Void response = restTemplate.patchForObject(targetBaseUrl + "/" + customerId, companionDTO, Void.class);
		} catch (RestClientException exception) {
			handleNotFoundException(exception, customerId);
		}
	}

	@SuppressWarnings("unused")
	@Override
	public void updateCompanionDetails(int customerId, CompanionDTO companionDTO) throws CustomerNotFoundException {
		try {
			HttpEntity<CompanionDTO> requestEntity = new HttpEntity<>(companionDTO);
			ResponseEntity<Void> responseEntity = restTemplate.exchange(targetBaseUrl + "/" + customerId,
					HttpMethod.PATCH, requestEntity, Void.class);
		} catch (RestClientException exception) {
			handleNotFoundException(exception, customerId);
		}
	}
	
	public void deleteCustomerUsingObject(int customerId) throws CustomerNotFoundException {
		try {
			restTemplate.delete(targetBaseUrl + "/" + customerId);
		} catch (RestClientException exception) {
			handleNotFoundException(exception, customerId);
		}
	}

	@SuppressWarnings("unused")
	@Override
	public void deleteCustomer(int customerId) throws CustomerNotFoundException {
		try {
			ResponseEntity<Void> responseEntity = restTemplate.exchange(targetBaseUrl + "/" + customerId,
					HttpMethod.DELETE, HttpEntity.EMPTY, Void.class);
		} catch (RestClientException exception) {
			handleNotFoundException(exception, customerId);
		}
	}

	private void handleException(RestClientException exception) {
		throw new RestClientException(extractErrorMessage(exception.getMessage()));
	}
	
	private void handleNotFoundException(RestClientException exception, int customerId)
			throws CustomerNotFoundException {
		if (exception instanceof HttpClientErrorException.NotFound) {
			throw new CustomerNotFoundException(String.format(customerNotFound, customerId));
		} else {
			handleException(exception);
		}
	}

	private String extractErrorMessage(String exceptionMessage) {
		Matcher matcher = Pattern.compile("\"message\":\"([^\"]+)\"").matcher(exceptionMessage);
		List<String> messages = new ArrayList<>();
		while (matcher.find()) {
			messages.add(matcher.group(1));
		}
		return messages.isEmpty() ? exceptionMessage : String.join("; ", messages);
	}

}
