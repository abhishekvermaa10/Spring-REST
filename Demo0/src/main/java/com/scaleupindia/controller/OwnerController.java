package com.scaleupindia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scaleupindia.service.OwnerService;

/**
 * @author abhishekvermaa10
 *
 */
@RequestMapping("/owners")
@Controller
public class OwnerController {
	@Autowired
	private OwnerService ownerService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public String createOwner() {
		return ownerService.saveOwner();
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public String getOwner() {
		return ownerService.findOwner();
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public String updatePetDetails() {
		return ownerService.updatePetDetails();
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE)
	public String deleteOwner() {
		return ownerService.deleteOwner();
	}

	@ResponseBody
	@RequestMapping(value = "/owners/all", method = RequestMethod.GET)
	public String getAllOwners() {
		return ownerService.findAllOwners();
	}
}
