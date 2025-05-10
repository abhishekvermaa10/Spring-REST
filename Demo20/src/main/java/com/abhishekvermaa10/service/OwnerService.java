package com.abhishekvermaa10.service;

import java.time.LocalDate;
import java.util.List;

import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.exception.ValidationException;

/**
 * @author abhishekvermaa10
 */
public interface OwnerService {

	Integer saveOwner(OwnerDTO ownerDTO);

	OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException;

	List<OwnerDTO> findByAllOwnersByPetDateOfBirthBetween(LocalDate startDate, LocalDate endDate)
			throws ValidationException;

}
