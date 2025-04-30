package com.abhishekvermaa10.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author abhishekvermaa10
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class ErrorDTO {

	@Schema(description = "The message providing more details about the error")
	private String message;
	@Schema(description = "The HTTP status enum representing the type of error")
	private HttpStatus error;
	@Schema(description = "The numeric HTTP status code of the error")
	private Integer status;
	@Schema(description = "The date and time when the error occurred")
	private LocalDateTime timestamp;

}
