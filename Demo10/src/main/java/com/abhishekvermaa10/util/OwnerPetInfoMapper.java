package com.abhishekvermaa10.util;

import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.abhishekvermaa10.dto.OwnerPetInfoDTO;

/**
 * @author abhishekvermaa10
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OwnerPetInfoMapper {

	String INVALID_ARRAY = "Invalid object array provided.";

	default OwnerPetInfoDTO mapObjectArrayToOwnerPetInfoDTO(Object[] array) {
		if (Objects.isNull(array) || array.length < 4) {
			throw new IllegalArgumentException(INVALID_ARRAY);
		} else {
			return OwnerPetInfoDTO.builder()
					.id((int) array[0])
					.firstName((String) array[1])
					.lastName((String) array[2])
					.petName((String) array[3])
					.build();
		}
	}

}
