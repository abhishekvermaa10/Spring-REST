package com.abhishekvermaa10.dto;

import java.time.LocalDate;

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
public class CompanionDTO {

	@EqualsAndHashCode.Include
	private int id;
	private String name;
	private String gender;
	private String type;
	private LocalDate birthDate;
	private String birthPlace;
	private String category;

}
