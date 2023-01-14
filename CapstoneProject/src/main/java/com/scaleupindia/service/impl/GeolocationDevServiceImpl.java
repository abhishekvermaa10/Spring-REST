package com.scaleupindia.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaleupindia.dto.GeolocationDTO;
import com.scaleupindia.dto.GeolocationsDTO;
import com.scaleupindia.service.GeolocationService;

/**
 * @author abhishekvermaa10
 *
 */
@Service
public class GeolocationDevServiceImpl implements GeolocationService {
	private ObjectMapper objectMapper = new ObjectMapper();
	@Value("classpath:geolocation.json")
	private Resource resourceFile;

	@Override
	public String fetchLocation(String city) {
		GeolocationsDTO geolocationsDTO;
		try {
			geolocationsDTO = objectMapper.readValue(resourceFile.getFile(), GeolocationsDTO.class);
			if (Objects.nonNull(geolocationsDTO) && !geolocationsDTO.getGeoLocationList().isEmpty()) {
				GeolocationDTO geolocationDTO = geolocationsDTO.getGeoLocationList().get(0);
				return geolocationDTO.getRegion();
			} else {
				return "NA";
			}
		} catch (Exception e) {
			return "NA";
		}
	}

}
