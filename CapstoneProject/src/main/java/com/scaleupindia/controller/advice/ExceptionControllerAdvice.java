package com.scaleupindia.controller.advice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.scaleupindia.dto.ErrorDTO;
import com.scaleupindia.exception.OwnerNotFoundException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

/**
 * @author abhishekvermaa10
 *
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleOwnerNotFoundException(OwnerNotFoundException exception) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setTimestamp(LocalDateTime.now());
		errorDTO.setStatus(HttpStatus.NOT_FOUND);
		errorDTO.setError(exception.getMessage());
		return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setTimestamp(LocalDateTime.now());
		errorDTO.setStatus(HttpStatus.BAD_REQUEST);
		errorDTO.setError(exception.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining(", ")));
		return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorDTO> handleConstraintViolationException(ConstraintViolationException exception) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setTimestamp(LocalDateTime.now());
		errorDTO.setStatus(HttpStatus.BAD_REQUEST);
		errorDTO.setError(exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.joining(", ")));
		return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
	}
}
