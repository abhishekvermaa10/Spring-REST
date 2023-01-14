package com.scaleupindia.controller.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

import com.scaleupindia.dto.ErrorDTO;
import com.scaleupindia.exception.CustomerNotFoundException;

/**
 * @author abhishekvermaa10
 *
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleCustomerNotFoundException(CustomerNotFoundException exception) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setTimestamp(LocalDateTime.now());
		errorDTO.setStatus(HttpStatus.NOT_FOUND);
		errorDTO.setError(exception.getMessage());
		return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleRestClientException(RestClientException exception) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setTimestamp(LocalDateTime.now());
		errorDTO.setStatus(HttpStatus.BAD_REQUEST);
		errorDTO.setError(exception.getMessage());
		return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
	}
}
