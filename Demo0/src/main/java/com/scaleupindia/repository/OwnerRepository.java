package com.scaleupindia.repository;

/**
 * @author abhishekvermaa10
 *
 */
public interface OwnerRepository {
	String saveOwner();

	String findOwner();

	String updatePetDetails();

	String deleteOwner();

	String findAllOwners();
}