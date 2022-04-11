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

import com.vega.app.dtos.RoleDTO;
import com.vega.app.dtos.SimpleRoleDTO;
import com.vega.app.entities.CustomResponse;
import com.vega.app.services.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping
	public ResponseEntity<CustomResponse<Set<RoleDTO>>> getAll() {
		return new ResponseEntity<>(new CustomResponse<>(roleService.getAll(),"Got all roles :)"), HttpStatus.OK);
	}

	@GetMapping("deleted-false")
	public ResponseEntity<CustomResponse<Set<RoleDTO>>> getByDeleted() {
		return new ResponseEntity<>(new CustomResponse<>(roleService.getAllDeleted(false),"Got all deleted false roles :)"), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CustomResponse<SimpleRoleDTO>> create(@RequestBody RoleDTO dto) {
		return new ResponseEntity<>(
				new CustomResponse<>(roleService.createWithPrivileges(dto), "Role created succuessfuly"),
				HttpStatus.CREATED);
	}

	@PatchMapping
	public ResponseEntity<CustomResponse<SimpleRoleDTO>> update(@RequestBody RoleDTO dto) {
		return new ResponseEntity<>(new CustomResponse<>(roleService.edit(dto), "Role updated succuessfuly"),
				HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<CustomResponse<RoleDTO>> delete(@PathVariable(name = "id") Long id) {
		roleService.delete(id);

		return new ResponseEntity<>(new CustomResponse<>(null, "Role deleted permanently"), HttpStatus.OK);
	}

}
