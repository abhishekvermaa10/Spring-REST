package com.scaleupindia.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

/**
 * @author abhishekvermaa10
 *
 */
public class ErrorDTO {
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String error;

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ErrorDTO [timestamp=" + timestamp + ", status=" + status + ", error=" + error + "]";
	}
}
