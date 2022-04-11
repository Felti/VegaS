package com.vega.app.restcontrollers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vega.app.dtos.CategoryDTO;
import com.vega.app.entities.CustomResponse;
import com.vega.app.services.CategoryService;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PostMapping
	public ResponseEntity<CustomResponse<CategoryDTO>> create(@RequestBody CategoryDTO category) {
		return new ResponseEntity<>(
				new CustomResponse<>(categoryService.create(category), "Your category has been created"), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<CustomResponse<Set<CategoryDTO>>> all(@RequestParam("deleted") Boolean deleted) {
		return new ResponseEntity<>(
				new CustomResponse<>(categoryService.getAll(deleted), "Got all categories :')"), HttpStatus.OK);
	}

}
