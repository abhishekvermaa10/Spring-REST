package com.abhishekvermaa10.service;

import java.util.List;

import com.abhishekvermaa10.dto.OwnerDTO;

/**
 * @author abhishekvermaa10
 */
public interface OwnerService {

	List<OwnerDTO> findAllOwners();
	
	List<OwnerDTO> findAllOwnersWithoutPets();

}
