package com.abhishekvermaa10.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	public Page<OwnerPetInfoDTO> findOwnerDetails(Pageable pageable) {
		Page<Object[]> page = ownerRepository.findIdAndFirstNameAndLastNameAndPetName(pageable);
		List<OwnerPetInfoDTO> detailsDTOList = page.stream()
				.map(ownerPetInfoMapper::mapObjectArrayToOwnerPetInfoDTO)
				.toList();
		return new PageImpl<>(detailsDTOList, pageable, page.getTotalElements());
	}

}
