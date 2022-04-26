package com.vega.app.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vega.app.dtos.TagDTO;
import com.vega.app.entities.CustomResponse;
import com.vega.app.services.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {

	@Autowired
	private TagService tagService;

	@PostMapping
	public ResponseEntity<CustomResponse<TagDTO>> create(@RequestBody TagDTO tagDTO) {
		return new ResponseEntity<>(new CustomResponse<>(tagService.create(tagDTO), "Your tag has been created"),
				HttpStatus.CREATED);
	}
}
