package com.abhishekvermaa10.service;

import java.util.List;

import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.exception.OwnerNotFoundException;

/**
 * @author abhishekvermaa10
 */
public interface OwnerService {

	Integer saveOwner(OwnerDTO ownerDTO);
	
	OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException;

	void updateOwner(int ownerId, OwnerDTO ownerDTO) throws OwnerNotFoundException;

	void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException;
	
	void deleteOwner(int ownerId) throws OwnerNotFoundException;

	List<OwnerDTO> findAllOwners();

}
