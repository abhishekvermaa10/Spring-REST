package com.abhishekvermaa10.service;

import com.abhishekvermaa10.exception.OwnerNotFoundException;

/**
 * @author abhishekvermaa10
 */
public interface OwnerService {
	
	String saveOwner();

	String findOwner() throws OwnerNotFoundException;
	
	String updateOwner() throws OwnerNotFoundException;

	String updatePetDetails() throws OwnerNotFoundException;

	String deleteOwner() throws OwnerNotFoundException;

	String findAllOwners();
	
}
