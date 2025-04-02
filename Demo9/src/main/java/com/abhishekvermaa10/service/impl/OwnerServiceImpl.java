package com.abhishekvermaa10.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.OwnerPetInfoDTO;
import com.abhishekvermaa10.repository.OwnerRepository;
import com.abhishekvermaa10.service.OwnerService;
import com.abhishekvermaa10.util.OwnerMapper;
import com.abhishekvermaa10.util.OwnerPetInfoMapper;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {

	private final OwnerRepository ownerRepository;
	private final OwnerMapper ownerMapper;
	private final OwnerPetInfoMapper ownerPetInfoMapper;

	@Override
	public List<OwnerDTO> findAllOwners() {
		return ownerRepository.findAll()
				.stream()
				.map(ownerMapper::ownerToOwnerDTO)
				.toList();
	}

	@Override
	public List<OwnerPetInfoDTO> findOwnerDetails(int pageNumber, int pageSize, String sortBy, boolean descending) {
		Direction direction = descending ? Direction.DESC : Direction.ASC;
		Sort sort = Sort.by(direction, sortBy);
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		return ownerRepository.findIdAndFirstNameAndLastNameAndPetNameList(pageable)
				.stream()
				.map(ownerPetInfoMapper::mapObjectArrayToOwnerPetInfoDTO)
				.toList();
	}

	@Override
	public List<OwnerPetInfoDTO> findOwnerDetailsAsList(Pageable pageable) {
		return ownerRepository.findIdAndFirstNameAndLastNameAndPetNameList(pageable)
				.stream()
				.map(ownerPetInfoMapper::mapObjectArrayToOwnerPetInfoDTO)
				.toList();
	}

	@Override
	public Page<OwnerPetInfoDTO> findOwnerDetailsAsPage(Pageable pageable) {
		Page<Object[]> page = ownerRepository.findIdAndFirstNameAndLastNameAndPetNamePage(pageable);
		List<OwnerPetInfoDTO> detailsDTOList = page.stream()
				.map(ownerPetInfoMapper::mapObjectArrayToOwnerPetInfoDTO)
				.toList();
		return new PageImpl<>(detailsDTOList, pageable, page.getTotalElements());
	}

}
