package com.abhishekvermaa10.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.abhishekvermaa10.dto.DomesticPetDTO;
import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.PetDTO;
import com.abhishekvermaa10.entity.DomesticPet;
import com.abhishekvermaa10.entity.Owner;
import com.abhishekvermaa10.entity.Pet;
import com.abhishekvermaa10.entity.WildPet;
import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.exception.ValidationException;
import com.abhishekvermaa10.repository.OwnerRepository;
import com.abhishekvermaa10.service.impl.OwnerServiceImpl;
import com.abhishekvermaa10.util.OwnerMapper;
import com.abhishekvermaa10.util.OwnerMapperImpl;

/**
 * @author abhishekvermaa10
 */
@TestPropertySource("classpath:messages.properties")
@SpringBootTest(classes = { OwnerServiceImpl.class, OwnerMapperImpl.class })
class OwnerServiceImplTest {

	@Autowired
	private OwnerServiceImpl ownerServiceImpl;
	@Autowired
	private OwnerMapper ownerMapper;
	@MockitoBean
	private OwnerRepository ownerRepository;

	@Test
	void test_SaveOwner_WhenOwnerDTOIsValid_ShouldSaveOwner() {
		// Given
		OwnerDTO inputOwnerDTO = new OwnerDTO(); 
		PetDTO inputDomesticPetDTO = new DomesticPetDTO();
		inputOwnerDTO.setPetDTO(inputDomesticPetDTO);
		Owner expectedOwner = ownerMapper.ownerDTOToOwner(inputOwnerDTO);
		when(ownerRepository.save(expectedOwner)).thenReturn(expectedOwner);
		// When
		Integer ownerId = ownerServiceImpl.saveOwner(inputOwnerDTO);
		// Then
		assertThat(ownerId).isEqualTo(expectedOwner.getId());
		verify(ownerRepository, times(1)).save(any(Owner.class));
	}

	@Test
	void test_FindOwner_WhenOwnerExists_ShouldReturnOwnerDTO() throws OwnerNotFoundException {
		// Given
		int inputOwnerId = 1;
		Owner expectedOwner = new Owner();
		Pet expectedDomesticPet = new DomesticPet();
		expectedOwner.setPet(expectedDomesticPet);
		when(ownerRepository.findById(inputOwnerId)).thenReturn(Optional.of(expectedOwner));
		// When
		OwnerDTO actualOwnerDTO = ownerServiceImpl.findOwner(inputOwnerId);
		// Then
		assertThat(actualOwnerDTO).isNotNull();
		verify(ownerRepository, times(1)).findById(inputOwnerId);
	}

	@Test
	void test_FindOwner_WhenOwnerDoesNotExist_ShouldThrowOwnerNotFoundException() {
		// Given
		int inputOwnerId = 1;
		when(ownerRepository.findById(inputOwnerId)).thenReturn(Optional.empty());
		String expectedMessage = String.format("Can't find owner with ownerId %s.", inputOwnerId);
		// When & Then
		assertThatThrownBy(() -> ownerServiceImpl.findOwner(inputOwnerId))
			.isInstanceOf(OwnerNotFoundException.class)
			.hasMessage(expectedMessage);
		verify(ownerRepository, times(1)).findById(inputOwnerId);
	}

	@Test
	void test_FindAllOwnersByPetDateOfBirthRange_WhenValidRange_ShouldReturnOwners() throws ValidationException {
		// Given
		LocalDate inputStartDate = LocalDate.of(2022, 1, 1);
		LocalDate inputEndDate = LocalDate.of(2023, 1, 1);
		Owner expectedOwner1 = new Owner();
		Pet expectedDomesticPet = new DomesticPet();
		expectedOwner1.setPet(expectedDomesticPet);
		Owner expectedOwner2 = new Owner();
		Pet expectedWildPet = new WildPet();
		expectedOwner2.setPet(expectedWildPet);
		List<Owner> expectedOwnerList = List.of(expectedOwner1, expectedOwner2);
		when(ownerRepository.findAllOwnersByPetDateOfBirthRange(inputStartDate, inputEndDate))
				.thenReturn(expectedOwnerList);
		// When
		List<OwnerDTO> actualOwnerDTOList = ownerServiceImpl.findByAllOwnersByPetDateOfBirthBetween(inputStartDate,
				inputEndDate);
		// Then
		assertThat(actualOwnerDTOList).hasSize(expectedOwnerList.size());
		verify(ownerRepository, times(1)).findAllOwnersByPetDateOfBirthRange(inputStartDate, inputEndDate);
	}

	@Test
	void test_FindAllOwnersByPetDateOfBirthRange_WhenInvalidDateRange_ShouldThrowValidationException() {
		// Given
		LocalDate inputStartDate = LocalDate.of(2023, 1, 1);
		LocalDate inputEndDate = LocalDate.of(2022, 1, 1);
		String expectedMessage = String.format("Start date %s can not be after end date %s.", inputStartDate, inputEndDate);
		// When & Then
		assertThatThrownBy(() -> ownerServiceImpl.findByAllOwnersByPetDateOfBirthBetween(inputStartDate, inputEndDate))
			.isInstanceOf(ValidationException.class)
			.hasMessage(expectedMessage);
	}

}
