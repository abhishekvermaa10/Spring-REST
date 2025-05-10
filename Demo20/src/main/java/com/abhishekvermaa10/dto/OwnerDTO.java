package com.abhishekvermaa10.dto;

import com.abhishekvermaa10.enums.Gender;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class OwnerDTO {

	@EqualsAndHashCode.Include
	private int id;
	@Size(max = 255, message = "{owner.first.name.length}")
	@NotBlank(message = "{owner.first.name.required}")
	private String firstName;
	@Size(max = 255, message = "{owner.last.name.length}")
	@NotBlank(message = "{owner.last.name.required}")
	private String lastName;
	@NotNull(message = "{owner.gender.required}")
	private Gender gender;
	@Size(max = 255, message = "{owner.city.length}")
	@NotBlank(message = "{owner.city.required}")
	private String city;
	@Size(max = 255, message = "{owner.state.length}")
	@NotBlank(message = "{owner.state.required}")
	private String state;
	@EqualsAndHashCode.Include
	@Size(min = 10, max = 10, message = "{owner.mobile.number.length}")
	@NotBlank(message = "{owner.mobile.number.required}")
	private String mobileNumber;
	@EqualsAndHashCode.Include
	@Email(message = "{owner.email.invalid}")
	@NotBlank(message = "{owner.email.required}")
	private String emailId;
	@Valid
	@NotNull(message = "{owner.pet.required}")
	private PetDTO petDTO;

}
