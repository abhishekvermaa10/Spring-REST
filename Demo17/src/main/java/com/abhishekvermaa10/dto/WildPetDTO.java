package com.abhishekvermaa10.dto;

import com.abhishekvermaa10.enums.Gender;
import com.abhishekvermaa10.enums.PetType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author abhishekvermaa10
 */
@NoArgsConstructor
@ToString(callSuper = true)
@Setter
@Getter
public class WildPetDTO extends PetDTO {

	@Schema(description = "Place of birth of the pet")
	@Size(max = 255, message = "{pet.birth.place.length}")
	@NotBlank(message = "{pet.birth.place.required}")
	private String birthPlace;

	@Builder
	public WildPetDTO(int id, String name, Gender gender, PetType type, OwnerDTO ownerDTO, String birthPlace) {
		super(id, name, gender, type, ownerDTO);
		this.birthPlace = birthPlace;
	}

}
