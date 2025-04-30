package com.abhishekvermaa10.dto;

import com.abhishekvermaa10.enums.Gender;

import io.swagger.v3.oas.annotations.media.Schema;
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

	@Schema(description = "Unique ID of the owner")
	@EqualsAndHashCode.Include
	private int id;
	@Schema(description = "First name of the owner")
	@Size(max = 255, message = "{owner.first.name.length}")
	@NotBlank(message = "{owner.first.name.required}")
	private String firstName;
	@Schema(description = "Last name of the owner")
	@Size(max = 255, message = "{owner.last.name.length}")
	@NotBlank(message = "{owner.last.name.required}")
	private String lastName;
	@Schema(description = "Gender of the owner")
	@NotNull(message = "{owner.gender.required}")
	private Gender gender;
	@Schema(description = "City of the owner")
	@Size(max = 255, message = "{owner.city.length}")
	@NotBlank(message = "{owner.city.required}")
	private String city;
	@Schema(description = "State of the owner")
	@Size(max = 255, message = "{owner.state.length}")
	@NotBlank(message = "{owner.state.required}")
	private String state;
	@Schema(description = "Mobile number of the owner")
	@EqualsAndHashCode.Include
	@Size(min = 10, max = 10, message = "{owner.mobile.number.length}")
	@NotBlank(message = "{owner.mobile.number.required}")
	private String mobileNumber;
	@Schema(description = "Email id of the owner")
	@EqualsAndHashCode.Include
	@Email(message = "{owner.email.invalid}")
	@NotBlank(message = "{owner.email.required}")
	private String emailId;
	@Schema(description = "Pet details of the owner")
	@Valid
	@NotNull(message = "{owner.pet.required}")
	private PetDTO petDTO;

}
