package com.vega.app.restcontrollers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vega.app.dtos.TagDTO;
import com.vega.app.dtos.simple.SimpleTagDTO;
import com.vega.app.entities.CustomResponse;
import com.vega.app.services.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {

	@Autowired
	private TagService tagService;

	@GetMapping
	public ResponseEntity<CustomResponse<Set<SimpleTagDTO>>> getAll() {
		return new ResponseEntity<>(new CustomResponse<>(tagService.getAll()), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CustomResponse<TagDTO>> create(@RequestBody TagDTO tagDTO) {
		return new ResponseEntity<>(new CustomResponse<>(tagService.create(tagDTO), "Your tag has been created"),
				HttpStatus.CREATED);
	}

	// @PatchMapping
	// public ResponseEntity<CustomResponse<TagDTO>> edit(@RequestBody TagDTO
	// tagDTO) {
	// return new ResponseEntity<>(
	// new CustomResponse<>(tagService.edit(tagDTO, tagDTO.getId()), "Votre tag a
	// été modifié avec succès."),
	// HttpStatus.OK);
	// }
	//
	// @GetMapping("{id}")
	// public ResponseEntity<CustomResponse<TagDTO>> getById(@PathVariable Long id)
	// {
	// return new ResponseEntity<>(new
	// CustomResponse<>(tagService.mapEntityToDTO(tagService.getById(id))),
	// HttpStatus.OK);
	// }
	//
	// @GetMapping("soft-delete/{id}")
	// public ResponseEntity<CustomResponse<TagDTO>> softDelete(@PathVariable(name =
	// "id") Long id) {
	// return new ResponseEntity<>(
	// new CustomResponse<>(tagService.softDelete(id), "Votre tag a été désactivé
	// avec succès."), HttpStatus.OK);
	// }
	//
	// @GetMapping("un-delete/{id}")
	// @PreAuthorize("hasAuthority(T(com.ged.enumerations.TagPrivilege).CAN_RESTORE_TAG.toString())")
	// public ResponseEntity<CustomResponse<TagDTO>> undelete(@PathVariable(name =
	// "id") Long id) {
	// return new ResponseEntity<>(new CustomResponse<>(tagService.undelete(id),
	// "Votre tag a été restauré avec succès."),
	// HttpStatus.OK);
	// }
}
