package com.abhishekvermaa10.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.abhishekvermaa10.dto.DomesticPetDTO;
import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.PetDTO;
import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.repository.OwnerRepository;
import com.abhishekvermaa10.service.OwnerService;
import com.abhishekvermaa10.util.OwnerMapper;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {

	private final MessageSource messageSource;
	private final OwnerRepository ownerRepository;
	private final OwnerMapper ownerMapper;
	private static final String OWNER_NOT_FOUND_KEY = "owner.not.found";

	@Override
	public OwnerDTO findOwner(int ownerId) throws OwnerNotFoundException {
		return ownerRepository.findById(ownerId)
				.map(ownerMapper::ownerToOwnerDTO)
				.map(this::formatDates)
				.orElseThrow(() -> new OwnerNotFoundException(String.format(getMessage(OWNER_NOT_FOUND_KEY), ownerId)));
	}
	
	private String getMessage(String key) {
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}

	private OwnerDTO formatDates(OwnerDTO ownerDTO) {
		PetDTO petDTO = ownerDTO.getPetDTO();
		if (petDTO instanceof DomesticPetDTO domesticPetDTO) {
			domesticPetDTO.setFormattedBirthDate(formatLocalDate(domesticPetDTO.getBirthDate()));
		}
		return ownerDTO;
	}

	private String formatLocalDate(LocalDate localDate) {
		return DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
				.withLocale(LocaleContextHolder.getLocale())
				.format(localDate);
	}

}
