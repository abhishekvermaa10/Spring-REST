package com.abhishekvermaa10.dto;

import com.abhishekvermaa10.enums.Gender;
import com.abhishekvermaa10.enums.PetType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author abhishekvermaa10
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "category")
@JsonSubTypes({ @Type(value = DomesticPetDTO.class, name = "Domestic"),
		@Type(value = WildPetDTO.class, name = "Wild") })
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Setter
@Getter
public abstract class PetDTO {

	@Schema(description = "Unique ID of the pet")
	@EqualsAndHashCode.Include
	private int id;
	@Schema(description = "Name of the pet")
	@Size(max = 255, message = "{pet.name.length}")
	@NotBlank(message = "{pet.name.required}")
	private String name;
	@Schema(description = "Gender of the pet")
	@NotNull(message = "{pet.gender.required}")
	private Gender gender;
	@Schema(description = "Type of the pet")
	@NotNull(message = "{pet.type.required}")
	private PetType type;
	@Schema(description = "Owner details of the pet")
	private OwnerDTO ownerDTO;

}
