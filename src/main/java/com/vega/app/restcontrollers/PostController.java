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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@Operation(summary = "Used to create a new post", description = "")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Your post has been created", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = PostDTO.class)) }, headers = @Header(name = "Authorization", description = "User token")),
			@ApiResponse(responseCode = "403", description = "Unauthorized", content = @Content, headers = @Header(name = "Authorization", description = "User token", required = true, schema = @Schema)) })
	@PostMapping
	public ResponseEntity<CustomResponse<PostDTO>> create(@RequestBody PostDTO post) {
		return new ResponseEntity<>(new CustomResponse<>(postService.create(post), "Your post has been created"),
				HttpStatus.CREATED);
	}

	@PostMapping("/all")
	public ResponseEntity<CustomResponse<Page<PostDTO>>> all(@RequestBody DocumentRequest request) {
		return new ResponseEntity<>(new CustomResponse<>(postService.getAll(request), "got all the posts :')"),
				HttpStatus.OK);
	}
}
