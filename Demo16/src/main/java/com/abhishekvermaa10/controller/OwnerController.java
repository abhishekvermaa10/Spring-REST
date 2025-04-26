package com.abhishekvermaa10.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.OwnerIdDTO;
import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.service.OwnerService;

import lombok.RequiredArgsConstructor;

/**
 * @author abhishekvermaa10
 */
@RequiredArgsConstructor
@RequestMapping("/owners")
@RestController
public class OwnerController {

	private final OwnerService ownerService;

	@PostMapping
	public ResponseEntity<EntityModel<OwnerIdDTO>> saveOwner(@RequestBody OwnerDTO ownerDTO) {
		Integer ownerId = ownerService.saveOwner(ownerDTO);
		OwnerIdDTO ownerIdDTO = new OwnerIdDTO(ownerId);
		EntityModel<OwnerIdDTO> entityModel = EntityModel.of(ownerIdDTO);
		try {
			entityModel.add(linkTo(methodOn(OwnerController.class).saveOwner(ownerDTO)).withSelfRel())
					.add(linkTo(methodOn(OwnerController.class).findOwner(ownerId)).withRel("findOwner"))
					.add(linkTo(methodOn(OwnerController.class).findAllOwners()).withRel("findAllOwners"));
		} catch (OwnerNotFoundException e) {
			return null;
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(entityModel);
	}

	@GetMapping("/{ownerId}")
	public ResponseEntity<EntityModel<OwnerDTO>> findOwner(@PathVariable int ownerId) throws OwnerNotFoundException {
		OwnerDTO ownerDTO = ownerService.findOwner(ownerId);
		EntityModel<OwnerDTO> entityModel = EntityModel.of(ownerDTO);
		entityModel.add(linkTo(methodOn(OwnerController.class).findOwner(ownerId)).withSelfRel())
				.add(linkTo(methodOn(OwnerController.class).findAllOwners()).withRel("findAllOwners"));
		return ResponseEntity.status(HttpStatus.OK).body(entityModel);
	}

	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<OwnerDTO>>> findAllOwners() {
		List<OwnerDTO> ownerDTOList = ownerService.findAllOwners();
		List<EntityModel<OwnerDTO>> entityModelList = ownerDTOList.stream()
				.map(ownerDTO -> {
					try {
						return EntityModel.of(ownerDTO)
								.add(linkTo(methodOn(OwnerController.class).findOwner(ownerDTO.getId())).withRel("findOwner"));
					} catch (OwnerNotFoundException e) {
						return null;
					}
				})
				.toList();
		CollectionModel<EntityModel<OwnerDTO>> collectionModel = CollectionModel.of(entityModelList);
		collectionModel.add(linkTo(methodOn(OwnerController.class).findAllOwners()).withSelfRel());
		return ResponseEntity.status(HttpStatus.OK).body(collectionModel);
	}

}
