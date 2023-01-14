package com.scaleupindia.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.scaleupindia.dto.GeolocationDTO;
import com.scaleupindia.dto.GeolocationsDTO;
import com.scaleupindia.service.GeolocationService;

/**
 * @author abhishekvermaa10
 *
 */
@Profile("prod")
@Service
public class GeolocationProdServiceImpl implements GeolocationService {
	@Autowired
	private RestTemplate restTemplate;
	@Value("${geolocation.url}")
	private String geolocationUrl;

	@Override
	public String fetchLocation(String city) {
		GeolocationsDTO geolocationsDTO = restTemplate.getForObject(geolocationUrl + city, GeolocationsDTO.class);
		if (Objects.nonNull(geolocationsDTO) && !geolocationsDTO.getGeoLocationList().isEmpty()) {
			GeolocationDTO geolocationDTO = geolocationsDTO.getGeoLocationList().get(0);
			return geolocationDTO.getRegion();
		} else {
			return "NA";
		}
	}

}
