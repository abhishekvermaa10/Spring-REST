package com.abhishekvermaa10.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.OwnerPetInfoDTO;

/**
 * @author abhishekvermaa10
 */
public interface OwnerService {

	List<OwnerDTO> findAllOwners();

	List<OwnerPetInfoDTO> findOwnerDetails(int pageNumber, int pageSize, String sortBy, boolean descending);

	List<OwnerPetInfoDTO> findOwnerDetailsAsList(Pageable pageable);

	Page<OwnerPetInfoDTO> findOwnerDetailsAsPage(Pageable pageable);

}
