package com.scaleupindia.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

/**
 * @author abhishekvermaa10
 *
 */
@Component
@Endpoint(id = "custom-info")
public class CustomInfoActuator {
	private Map<String, String> customInfoMap = new HashMap<>();

	@WriteOperation
	public void addCustomInfo(@Selector String key, @Selector String value) {
		customInfoMap.putIfAbsent(key, value);
	}

	@ReadOperation
	public String getCustomInfo(@Selector String key) {
		return customInfoMap.get(key);
	}

	@DeleteOperation
	public void removeCustomInfo(@Selector String key) {
		customInfoMap.remove(key);
	}

	@ReadOperation
	public Map<String, String> getAllCustomInfo() {
		return customInfoMap;
	}
}
