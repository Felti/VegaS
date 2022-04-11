package com.vega.app.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vega.app.dtos.DocumentRequest;
import com.vega.app.dtos.PostDTO;
import com.vega.app.entities.CustomResponse;
import com.vega.app.services.PostService;

@RestController
@RequestMapping("api/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping
	public ResponseEntity<CustomResponse<PostDTO>> create(@RequestBody PostDTO post) {
		return new ResponseEntity<>(new CustomResponse<>(postService.create(post), "Your post has been created"),
				HttpStatus.OK);
	}

	@PostMapping("/all")
	public ResponseEntity<CustomResponse<Page<PostDTO>>> all(@RequestBody DocumentRequest request) {
		return new ResponseEntity<>(new CustomResponse<>(postService.getAll(request),"got all the posts :')"), HttpStatus.OK);
	}
}
