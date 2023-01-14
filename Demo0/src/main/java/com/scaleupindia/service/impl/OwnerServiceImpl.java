package com.scaleupindia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scaleupindia.repository.OwnerRepository;
import com.scaleupindia.service.OwnerService;

/**
 * @author abhishekvermaa10
 *
 */
@Service
public class OwnerServiceImpl implements OwnerService {
	@Autowired
	private OwnerRepository ownerRepository;

	@Override
	public String saveOwner() {
		return ownerRepository.saveOwner();
	}

	@Override
	public String findOwner() {
		return ownerRepository.findOwner();
	}

	@Override
	public String updatePetDetails() {
		return ownerRepository.updatePetDetails();
	}

	@Override
	public String deleteOwner() {
		return ownerRepository.deleteOwner();
	}

	@Override
	public String findAllOwners() {
		return ownerRepository.findAllOwners();
	}

}
