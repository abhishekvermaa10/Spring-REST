package com.abhishekvermaa10.util;

import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import com.abhishekvermaa10.dto.DomesticPetDTO;
import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.PetDTO;
import com.abhishekvermaa10.dto.WildPetDTO;
import com.abhishekvermaa10.entity.DomesticPet;
import com.abhishekvermaa10.entity.Owner;
import com.abhishekvermaa10.entity.Pet;
import com.abhishekvermaa10.entity.WildPet;

/**
 * @author abhishekvermaa10
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OwnerMapper {

	String UNSUPPORTED_PET_INSTANCE = "Unsupported pet instance: %s";

	@Mapping(source = "petDTO", target = "pet")
	Owner ownerDTOToOwner(OwnerDTO ownerDTO);

	default Pet petDTOToPet(PetDTO petDTO) {
		return switch (petDTO) {
		case DomesticPetDTO domesticPetDTO -> domesticPetDTOToDomesticPet(domesticPetDTO);
		case WildPetDTO wildPetDTO -> wildPetDTOToWildPet(wildPetDTO);
		default -> throw new IllegalArgumentException(String.format(UNSUPPORTED_PET_INSTANCE, petDTO.getClass()));
		};
	}

	@Mapping(target = "owner", ignore = true)
	DomesticPet domesticPetDTOToDomesticPet(DomesticPetDTO domesticPetDTO);

	@Mapping(target = "owner", ignore = true)
	WildPet wildPetDTOToWildPet(WildPetDTO wildPetDTO);

	@Mapping(source = "pet", target = "petDTO")
	OwnerDTO ownerToOwnerDTO(Owner owner);

	default PetDTO petToPetDTO(Pet pet) {
		return switch (pet) {
		case DomesticPet domesticPet -> domesticPetToDomesticPetDTO(domesticPet);
		case WildPet wildPet -> wildPetToWildPetDTO(wildPet);
		default -> throw new IllegalArgumentException(String.format(UNSUPPORTED_PET_INSTANCE, pet.getClass()));
		};
	}

	@Mapping(target = "ownerDTO", ignore = true)
	DomesticPetDTO domesticPetToDomesticPetDTO(DomesticPet domesticPet);

	@Mapping(target = "ownerDTO", ignore = true)
	WildPetDTO wildPetToWildPetDTO(WildPet wildPet);
	
	default void customUpdateOwnerFromDTO(OwnerDTO ownerDTO, Owner owner) {
        updateOwnerFromDTO(ownerDTO, owner);
        if (Objects.nonNull(ownerDTO.getPetDTO()) && Objects.nonNull(owner.getPet())) {
            updatePetFromDTO(ownerDTO.getPetDTO(), owner.getPet());
        }
    }

	@Mapping(source = "petDTO", target = "pet", ignore = true)
	void updateOwnerFromDTO(OwnerDTO ownerDTO, @MappingTarget Owner owner);

	default void updatePetFromDTO(PetDTO petDTO, Pet pet) {
		switch (petDTO) {
		case DomesticPetDTO domesticPetDTO -> updateDomesticPetFromDTO(domesticPetDTO, (DomesticPet) pet);
		case WildPetDTO wildPetDTO -> updateWildPetFromDTO(wildPetDTO, (WildPet) pet);
		default -> throw new IllegalArgumentException(String.format(UNSUPPORTED_PET_INSTANCE, petDTO.getClass()));
		}
	}

	@Mapping(target = "owner", ignore = true)
	void updateDomesticPetFromDTO(DomesticPetDTO domesticPetDTO, @MappingTarget DomesticPet domesticPet);

	@Mapping(target = "owner", ignore = true)
	void updateWildPetFromDTO(WildPetDTO wildPetDTO, @MappingTarget WildPet wildPet);

}
