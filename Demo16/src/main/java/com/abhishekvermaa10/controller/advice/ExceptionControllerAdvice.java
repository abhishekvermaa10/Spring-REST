package com.abhishekvermaa10.controller.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.abhishekvermaa10.dto.ErrorDTO;
import com.abhishekvermaa10.exception.OwnerNotFoundException;

/**
 * @author abhishekvermaa10
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleOwnerNotFoundException(OwnerNotFoundException exception) {
		ErrorDTO errorDTO = ErrorDTO.builder()
				.message(exception.getMessage())
				.error(HttpStatus.NOT_FOUND)
				.status(HttpStatus.NOT_FOUND.value())
				.timestamp(LocalDateTime.now())
				.build();
		return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException exception) {
		ErrorDTO errorDTO = ErrorDTO.builder()
				.message(exception.getMessage())
				.error(HttpStatus.METHOD_NOT_ALLOWED)
				.status(HttpStatus.METHOD_NOT_ALLOWED.value())
				.timestamp(LocalDateTime.now())
				.build();
		return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleGenericException(Exception exception) {
		ErrorDTO errorDTO = ErrorDTO.builder()
				.message(exception.getMessage())
				.error(HttpStatus.INTERNAL_SERVER_ERROR)
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.timestamp(LocalDateTime.now())
				.build();
		return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
	}

}
