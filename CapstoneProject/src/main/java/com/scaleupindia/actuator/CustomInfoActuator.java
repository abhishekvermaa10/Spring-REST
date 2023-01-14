package com.scaleupindia.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

/**
 * @author abhishekvermaa10
 *
 */
@Component
@Endpoint(id = "custom-info")
public class CustomInfoActuator {
	@Value("${spring.application.name}")
	private String applicationName;

	@ReadOperation
	public String getAllCustomInfo() {
		return applicationName;
	}
}
