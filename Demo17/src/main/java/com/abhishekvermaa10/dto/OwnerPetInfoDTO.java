package com.abhishekvermaa10.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author abhishekvermaa10
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Setter
@Getter
public class OwnerPetInfoDTO {

	@Schema(description = "Unique ID of the owner")
	@EqualsAndHashCode.Include
	private int id;
	@Schema(description = "First name of the owner")
	private String firstName;
	@Schema(description = "Last name of the owner")
	private String lastName;
	@Schema(description = "Name of the pet")
	private String petName;

}
