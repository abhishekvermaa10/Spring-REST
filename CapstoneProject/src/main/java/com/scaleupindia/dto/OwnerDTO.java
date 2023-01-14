package com.scaleupindia.dto;

import com.scaleupindia.enums.Gender;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author abhishekvermaa10
 *
 */
public class OwnerDTO {
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
	private String state;
	@Size(min = 10, max = 10, message = "{owner.mobile.number.length}")
	private String mobileNumber;
	@Email(message = "{owner.email.invalid}")
	@NotBlank(message = "{owner.email.required}")
	private String emailId;
	@Valid
	@NotNull(message = "{owner.pet.required}")
	private PetDTO petDTO;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public PetDTO getPetDTO() {
		return petDTO;
	}

	public void setPetDTO(PetDTO petDTO) {
		this.petDTO = petDTO;
	}

	@Override
	public String toString() {
		return "OwnerDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", city=" + city + ", state=" + state + ", mobileNumber=" + mobileNumber + ", emailId=" + emailId
				+ ", petDTO=" + petDTO + "]";
	}
}
