package com.abhishekvermaa10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.service.OwnerService;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@RequestMapping(value = "/owners")
@Controller
public class OwnerController {
	
	private final OwnerService ownerService;
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public String saveOwner() {
		return ownerService.saveOwner();
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public String findOwner() {
		try {
			return ownerService.findOwner();
		} catch (OwnerNotFoundException e) {
			return e.getMessage();
		}
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public String updateOwner() {
		try {
			return ownerService.updateOwner();
		} catch (OwnerNotFoundException e) {
			return e.getMessage();
		}
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.PATCH)
	public String updatePetDetails() {
		try {
			return ownerService.updatePetDetails();
		} catch (OwnerNotFoundException e) {
			return e.getMessage();
		}
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE)
	public String deleteOwner() {
		try {
			return ownerService.deleteOwner();
		} catch (OwnerNotFoundException e) {
			return e.getMessage();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String findAllOwners() {
		return ownerService.findAllOwners();
	}

}
