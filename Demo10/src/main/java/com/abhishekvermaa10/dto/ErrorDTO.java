package com.abhishekvermaa10.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

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
@JacksonXmlRootElement(localName = "errorDTO")
public class ErrorDTO {

	private String message;
	private HttpStatus error;
	private Integer status;
	private LocalDateTime timestamp;

}
