package com.scaleupindia.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author abhishekvermaa10
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeolocationsDTO {
	@JsonProperty("data")
	private List<GeolocationDTO> geoLocationList;

	public List<GeolocationDTO> getGeoLocationList() {
		return geoLocationList;
	}

	public void setGeoLocationList(List<GeolocationDTO> geoLocationList) {
		this.geoLocationList = geoLocationList;
	}

	@Override
	public String toString() {
		return "GeolocationsDTO [geoLocationList=" + geoLocationList + "]";
	}

}
