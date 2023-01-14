package com.scaleupindia.validator;

/**
 * @author abhishekvermaa10
 *
 */
public class InputValidator {
	private InputValidator() {

	}

	public static int validatePageNumber(int pageNumber) {
		if (pageNumber < 0) {
			return 0;
		} else {
			return pageNumber;
		}
	}

	public static int validateItemsPerPage(int itemsPerPage) {
		if (itemsPerPage < 0) {
			return 0;
		} else {
			return itemsPerPage;
		}
	}

	public static String validateSortingParameter(String sortingParameter) {
		if ("id".equals(sortingParameter) || "firstName".equals(sortingParameter) || "lastName".equals(sortingParameter)
				|| "gender".equals(sortingParameter) || "city".equals(sortingParameter)
				|| "state".equals(sortingParameter) || "pet.id".equals(sortingParameter)
				|| "pet.name".equals(sortingParameter) || "pet.gender".equals(sortingParameter)
				|| "pet.type".equals(sortingParameter) || "pet.birthDate".equals(sortingParameter)) {
			return sortingParameter;
		} else {
			return "id";
		}
	}
}
