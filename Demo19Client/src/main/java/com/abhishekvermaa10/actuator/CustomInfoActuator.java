package com.abhishekvermaa10.actuator;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

/**
 * @author abhishekvermaa10
 */
@Endpoint(id = "custom-info")
@Component
public class CustomInfoActuator {

	private Long startTime = System.currentTimeMillis();
	private boolean displayUpTime = true;

	@ReadOperation
	public String getUptime() {
		if (displayUpTime) {
			long uptimeMillis = System.currentTimeMillis() - startTime;
			long seconds = (uptimeMillis / 1000) % 60;
			long minutes = (uptimeMillis / 1000) / 60;
			return String.format("Application is up since %d minutes and %d seconds.", minutes, seconds);
		} else {
			return "Uptime tracking is disabled.";
		}
	}
	
	@WriteOperation
	public String toggleUptimeTracking(@Selector String action) {
        if ("enable".equalsIgnoreCase(action)) {
            displayUpTime = true;
            return "Uptime tracking enabled.";
        } else if ("disable".equalsIgnoreCase(action)) {
            displayUpTime = false;
            return "Uptime tracking disabled.";
        } else {
            return "Invalid action. Use 'enable' or 'disable'.";
        }
    }
	
	@DeleteOperation
	public void resetUptimeTracking() {
		startTime = System.currentTimeMillis();
	}

}
