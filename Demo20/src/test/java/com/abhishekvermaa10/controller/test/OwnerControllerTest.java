package com.abhishekvermaa10.controller.test;

import static com.abhishekvermaa10.util.TestDataUtil.convertToJson;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.abhishekvermaa10.controller.OwnerController;
import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.exception.ValidationException;
import com.abhishekvermaa10.service.OwnerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * @author abhishekvermaa10
 */
@WebMvcTest(OwnerController.class)
class OwnerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockitoBean
	private OwnerService ownerService;
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@BeforeAll
	public static void beforeAll() {
		objectMapper.registerModule(new JavaTimeModule());
	}
	
	@Test
	void testSaveOwner_WhenOwnerDTOIsValid_ShouldReturnCreated() throws Exception {
		//Given
		String jsonFileName = "owner_input.json";
		String jsonInput = convertToJson(jsonFileName);
		OwnerDTO inputOwnerDTO = objectMapper.readValue(jsonInput, OwnerDTO.class);
		int expectedOwnerId = 1;
		when(ownerService.saveOwner(inputOwnerDTO)).thenReturn(expectedOwnerId);
		//When & Then
		mockMvc.perform(post("/owners")
				.contentType(APPLICATION_JSON)
				.content(jsonInput))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$").value(expectedOwnerId));
	}
	
	@Test
	void testSaveOwner_WhenOwnerDTOIsInvalid_ShouldReturnBadRequest() throws Exception {
		// Given
		String jsonFileName = "owner_input_empty.json";
		String jsonInput = convertToJson(jsonFileName);
		// When & Then
		mockMvc.perform(post("/owners")
				.contentType(APPLICATION_JSON)
				.content(jsonInput))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.[0].message").exists());
	}
	
	@Test
	void testFindOwner_WhenOwnerIdIsValid_ShouldReturnOk() throws Exception {
		// Given
		String jsonFileName = "owner_output.json";
		String expectedJson = convertToJson(jsonFileName);
		OwnerDTO expectedOwnerDTO = objectMapper.readValue(expectedJson, OwnerDTO.class);
		int inputOwnerId = 1;
		when(ownerService.findOwner(inputOwnerId)).thenReturn(expectedOwnerDTO);
		// When & Then
		mockMvc.perform(get("/owners/" + inputOwnerId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(expectedOwnerDTO.getId()))
				.andExpect(jsonPath("$.firstName").value(expectedOwnerDTO.getFirstName()))
				.andExpect(jsonPath("$.lastName").value(expectedOwnerDTO.getLastName()))
				.andExpect(jsonPath("$.gender").value(expectedOwnerDTO.getGender().name()))
				.andExpect(jsonPath("$.city").value(expectedOwnerDTO.getCity()))
				.andExpect(jsonPath("$.state").value(expectedOwnerDTO.getState()))
				.andExpect(jsonPath("$.mobileNumber").value(expectedOwnerDTO.getMobileNumber()))
				.andExpect(jsonPath("$.emailId").value(expectedOwnerDTO.getEmailId()))
				.andExpect(jsonPath("$.petDTO.id").value(expectedOwnerDTO.getPetDTO().getId()))
				.andExpect(jsonPath("$.petDTO.name").value(expectedOwnerDTO.getPetDTO().getName()))
				.andExpect(jsonPath("$.petDTO.gender").value(expectedOwnerDTO.getPetDTO().getGender().name()))
				.andExpect(jsonPath("$.petDTO.type").value(expectedOwnerDTO.getPetDTO().getType().name()));
	}
	
	@Test
	void testFindOwner_WhenOwnerIdIsInvalid_ShouldReturnBadRequest() throws Exception {
		// Given
		int inputOwnerId = 0;
		// When & Then
		mockMvc.perform(get("/owners/" + inputOwnerId))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.[0].message").exists());
	}
	
	@Test
	void testFindOwner_WhenOwnerIdIsValid_ShouldReturnNotFound() throws Exception {
		// Given
		int inputOwnerId = 1;
		String expectedMessage = String.format("Can't find owner with ownerId %s.", inputOwnerId);
		when(ownerService.findOwner(inputOwnerId)).thenThrow(new OwnerNotFoundException(expectedMessage));
		// When & Then
		mockMvc.perform(get("/owners/" + inputOwnerId))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message").value(expectedMessage));
	}
	
	@Test
	void testGetAllOwnersByPetDateOfBirthBetween_WhenValidDates_ShouldReturnOk() throws Exception {
		// Given
		String jsonFileName = "owners_output.json";
		String expectedJson = convertToJson(jsonFileName);
		List<OwnerDTO> expectedOwnerDTOList = objectMapper.readValue(expectedJson, new TypeReference<List<OwnerDTO>>() {});
		LocalDate inputStartDate = LocalDate.of(2020, 1, 1);
		LocalDate inputEndDate = LocalDate.of(2021, 12, 31);
		when(ownerService.findByAllOwnersByPetDateOfBirthBetween(inputStartDate, inputEndDate)).thenReturn(expectedOwnerDTOList);
		// When & Then
		mockMvc.perform(get("/owners/pets/dob")
				.param("startDate", inputStartDate.toString())
				.param("endDate", inputEndDate.toString()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id").value(expectedOwnerDTOList.get(0).getId()))
				.andExpect(jsonPath("$.[0].firstName").value(expectedOwnerDTOList.get(0).getFirstName()))
				.andExpect(jsonPath("$.[0].lastName").value(expectedOwnerDTOList.get(0).getLastName()))
				.andExpect(jsonPath("$.[0].gender").value(expectedOwnerDTOList.get(0).getGender().name()))
				.andExpect(jsonPath("$.[0].city").value(expectedOwnerDTOList.get(0).getCity()))
				.andExpect(jsonPath("$.[0].state").value(expectedOwnerDTOList.get(0).getState()))
				.andExpect(jsonPath("$.[0].mobileNumber").value(expectedOwnerDTOList.get(0).getMobileNumber()))
				.andExpect(jsonPath("$.[0].emailId").value(expectedOwnerDTOList.get(0).getEmailId()))
				.andExpect(jsonPath("$.[0].petDTO.id").value(expectedOwnerDTOList.get(0).getPetDTO().getId()))
				.andExpect(jsonPath("$.[0].petDTO.name").value(expectedOwnerDTOList.get(0).getPetDTO().getName()))
				.andExpect(jsonPath("$.[0].petDTO.gender").value(expectedOwnerDTOList.get(0).getPetDTO().getGender().name()))
				.andExpect(jsonPath("$.[0].petDTO.type").value(expectedOwnerDTOList.get(0).getPetDTO().getType().name()));
	}
	
	@Test
	void testGetAllOwnersByPetDateOfBirthBetween_WhenAnyDateIsFuture_ShouldReturnBadRequest() throws Exception {
		// Given
		LocalDate inputStartDate = LocalDate.of(2025, 1, 1);
		LocalDate inputEndDate = LocalDate.of(2025, 12, 31);
		// When & Then
		mockMvc.perform(get("/owners/pets/dob")
				.param("startDate", inputStartDate.toString())
				.param("endDate", inputEndDate.toString()))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.[0].message").exists());
	}

	@Test
	void testGetAllOwnersByPetDateOfBirthBetween_WhenStartDateAfterEndDate_ShouldReturnConflict() throws Exception {
		// Given
		LocalDate inputStartDate = LocalDate.of(2023, 1, 1);
		LocalDate inputEndDate = LocalDate.of(2022, 12, 31);
		String expectedMessage = String.format("Start date %s can not be after end date %s.", inputStartDate, inputEndDate);
		when(ownerService.findByAllOwnersByPetDateOfBirthBetween(inputStartDate, inputEndDate)).thenThrow(new ValidationException(expectedMessage));
		// When & Then
		mockMvc.perform(get("/owners/pets/dob")
				.param("startDate", inputStartDate.toString())
				.param("endDate", inputEndDate.toString()))
				.andExpect(status().isConflict())
				.andExpect(jsonPath("$.message").value(expectedMessage));
	}

}
