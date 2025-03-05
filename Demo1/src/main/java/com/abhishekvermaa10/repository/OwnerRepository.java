package com.abhishekvermaa10.repository;

/**
 * @author abhishekvermaa10
 */
public interface OwnerRepository {

	String save();

	String find();

	String updateOwner();
	
	String updatePetDetails();

	String delete();

	String findAll();

}
