package com.abhishekvermaa10.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author abhishekvermaa10
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class UpdatePetDTO {

	@Size(max = 255, message = "{pet.name.length}")
	@NotBlank(message = "{pet.name.required}")
	private String name;

}
