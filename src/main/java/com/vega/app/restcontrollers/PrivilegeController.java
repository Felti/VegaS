package com.vega.app.restcontrollers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vega.app.dtos.PrivilegeDTO;
import com.vega.app.entities.CustomResponse;
import com.vega.app.services.PrivilegeService;


@RestController
@RequestMapping("/api/privileges")
public class PrivilegeController {
	
	@Autowired
	private PrivilegeService privilegeService;
	
	
	@GetMapping
	public ResponseEntity<CustomResponse<Set<PrivilegeDTO>>> getAll() {
		return new ResponseEntity<>(new CustomResponse<>(privilegeService.getAll(),"Got all privileges :)"), HttpStatus.OK);
	}
	
	@GetMapping("/deleted-false")
	public ResponseEntity<CustomResponse<Set<PrivilegeDTO>>> getAllDeleted() {
		return new ResponseEntity<>(new CustomResponse<>(privilegeService.getAllDeleted(),"Got all privileges :)"), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<CustomResponse<PrivilegeDTO>> create(@RequestBody PrivilegeDTO privilegeDto) {
		return new ResponseEntity<>(new CustomResponse<>(privilegeService.create(privilegeDto.getName()),
				"Privilege successfuly created."), HttpStatus.CREATED);
	}
	
	@PatchMapping
	public ResponseEntity<CustomResponse<PrivilegeDTO>> update(@RequestBody PrivilegeDTO privilegeDto) {
		return new ResponseEntity<>(
				new CustomResponse<>(privilegeService.edit(privilegeDto), "Privilege successfuly updated"),
				HttpStatus.OK);
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<CustomResponse<PrivilegeDTO>> softDelete(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(new CustomResponse<>(privilegeService.softDelete(id), "Privilge successfully deleted."),
				HttpStatus.OK);
	}
	
	@GetMapping("/undelete/{id}")
	public ResponseEntity<CustomResponse<PrivilegeDTO>> undelete(@PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(new CustomResponse<>(privilegeService.undelete(id), "Privilge successfully restored."),
				HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<CustomResponse<PrivilegeDTO>> delete(@PathVariable(name = "id") Long id) {
		privilegeService.delete(id);

		return new ResponseEntity<>(new CustomResponse<>(null, "Privilge permanently deleted."),
				HttpStatus.OK);
	}

}
