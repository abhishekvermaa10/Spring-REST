package com.scaleupindia.repository.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.scaleupindia.repository.OwnerRepository;

/**
 * @author abhishekvermaa10
 *
 */
@Repository
public class OwnerRepositoryImpl implements OwnerRepository {
	@Value("${owner.saved}")
	private String ownerSaved;
	@Value("${owner.found}")
	private String ownerFound;
	@Value("${owner.pet.updated}")
	private String ownerPetUpdated;
	@Value("${owner.removed}")
	private String ownerDeleted;
	@Value("${owner.list.found}")
	private String ownerListFound;

	@Override
	public String saveOwner() {
		return ownerSaved;
	}

	@Override
	public String findOwner() {
		return ownerFound;
	}

	@Override
	public String updatePetDetails() {
		return ownerPetUpdated;
	}

	@Override
	public String deleteOwner() {
		return ownerDeleted;
	}

	@Override
	public String findAllOwners() {
		return ownerListFound;
	}
}
