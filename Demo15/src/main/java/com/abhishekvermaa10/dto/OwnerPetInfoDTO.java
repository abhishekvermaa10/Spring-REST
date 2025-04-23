package com.abhishekvermaa10.dto;

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

	@EqualsAndHashCode.Include
	private int id;
	private String firstName;
	private String lastName;
	private String petName;

}
