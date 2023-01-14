package com.scaleupindia.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scaleupindia.dto.OwnerDTO;
import com.scaleupindia.entity.Owner;
import com.scaleupindia.entity.Pet;
import com.scaleupindia.exception.OwnerNotFoundException;
import com.scaleupindia.repository.OwnerRepository;
import com.scaleupindia.service.GeolocationService;
import com.scaleupindia.service.OwnerService;
import com.scaleupindia.util.MapperUtil;
import com.scaleupindia.validator.InputValidator;

/**
 * @author abhishekvermaa10
 *
 */
@Service
public class OwnerServiceImpl implements OwnerService {
	@Autowired
	private GeolocationService geolocationService;
	@Autowired
	private OwnerRepository ownerRepository;
	@Value("${owner.not.found}")
	private String ownerNotFound;

	@Override
	public void saveOwner(OwnerDTO ownerDTO) {
		String state = geolocationService.fetchLocation(ownerDTO.getCity());
		ownerDTO.setState(state);
		Owner owner = MapperUtil.convertOwnerDtoToEntity(ownerDTO);
		ownerRepository.save(owner);
	}

	@Override
	public OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException {
		Optional<Owner> optionalOwner = ownerRepository.findById(ownerId);
		if (optionalOwner.isEmpty()) {
			throw new OwnerNotFoundException(ownerNotFound + ownerId);
		} else {
			Owner owner = optionalOwner.get();
			return MapperUtil.convertOwnerEntityToDto(owner);
		}
	}

	@Override
	public void updatePetDetails(int ownerId, String petName) throws OwnerNotFoundException {
		Optional<Owner> optionalOwner = ownerRepository.findById(ownerId);
		if (optionalOwner.isEmpty()) {
			throw new OwnerNotFoundException(ownerNotFound + ownerId);
		} else {
			Owner owner = optionalOwner.get();
			Pet pet = owner.getPet();
			pet.setName(petName);
			owner.setPet(pet);
			ownerRepository.save(owner);
		}
	}

	@Override
	public void deleteOwner(int ownerId) throws OwnerNotFoundException {
		Optional<Owner> optionalOwner = ownerRepository.findById(ownerId);
		if (optionalOwner.isEmpty()) {
			throw new OwnerNotFoundException(ownerNotFound + ownerId);
		} else {
			ownerRepository.deleteById(ownerId);
		}
	}

	@Override
	public long findTotalNumberOfOwners() {
		return ownerRepository.count();
	}

	@Override
	public List<OwnerDTO> findAllOwners(int pageNumber, int itemsPerPage, String sortingParameter,
			boolean sortDescending) {
		Sort.Direction modifiedDirection = Sort.Direction.ASC;
		if (sortDescending) {
			modifiedDirection = Sort.Direction.DESC;
		}
		String modifiedSortingParameter = InputValidator.validateSortingParameter(sortingParameter);
		Sort sort = Sort.by(modifiedDirection, modifiedSortingParameter);
		int modifiedItemsPerPage = InputValidator.validateItemsPerPage(itemsPerPage);
		int modifiedPageNumber = InputValidator.validatePageNumber(pageNumber);
		if (modifiedPageNumber > (findTotalNumberOfOwners() / modifiedItemsPerPage)) {
			modifiedPageNumber = 0;
		}
		PageRequest pageRequest = PageRequest.of(modifiedPageNumber, modifiedItemsPerPage, sort);
		return ownerRepository.findAll(pageRequest).stream().map(MapperUtil::convertOwnerEntityToDto).toList();
	}
}
