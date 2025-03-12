package com.abhishekvermaa10.dto;

import java.time.LocalDate;

import com.abhishekvermaa10.enums.Gender;
import com.abhishekvermaa10.enums.PetType;

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
public class DomesticPetDTO extends PetDTO {

	private LocalDate birthDate;

	@Builder
	public DomesticPetDTO(int id, String name, Gender gender, PetType type, OwnerDTO ownerDTO, LocalDate birthDate) {
		super(id, name, gender, type, ownerDTO);
		this.birthDate = birthDate;
	}

}
