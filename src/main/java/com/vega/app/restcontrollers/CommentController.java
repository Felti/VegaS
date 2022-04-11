package com.vega.app.restcontrollers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vega.app.dtos.CommentDTO;
import com.vega.app.entities.CustomResponse;
import com.vega.app.services.CommentService;

@RestController
@RequestMapping("api/comments")
public class CommentController {

	@Autowired
	CommentService commentService;

	@PostMapping
	public ResponseEntity<CustomResponse<CommentDTO>> create(@RequestBody CommentDTO comment) {
		return new ResponseEntity<>(new CustomResponse<>(commentService.create(comment), "Your comment has been created"),
				HttpStatus.OK);
	}

	@PatchMapping
	public ResponseEntity<CustomResponse<CommentDTO>> edit(@RequestBody CommentDTO comment) {
		return new ResponseEntity<>(new CustomResponse<>(commentService.edit(comment), "Your comment has been edited"),
				HttpStatus.OK);
	}

	@GetMapping("delete/{id}")
	public ResponseEntity<CustomResponse<CommentDTO>> solfDeleted(@RequestParam Long id) {
		return new ResponseEntity<>(new CustomResponse<>(commentService.softDelete(id), "Your comment has been deleted"),
				HttpStatus.OK);
	}

	@GetMapping("undelete/{id}")
	public ResponseEntity<CustomResponse<CommentDTO>> undelete(@RequestParam Long id) {
		return new ResponseEntity<>(new CustomResponse<>(commentService.undelete(id), "Your comment has been restored"),
				HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<CustomResponse<Set<CommentDTO>>> getSimpleCommentOfPost(Long id) {
		return new ResponseEntity<>(
				new CustomResponse<>(commentService.getSimpleCommentsOfPost(id), "Got all comments of this posts :')"), HttpStatus.OK);
	}
	
	@GetMapping("/full")
	public ResponseEntity<CustomResponse<Set<CommentDTO>>> getFullCommentOfPost(Long id) {
		return new ResponseEntity<>(
				new CustomResponse<>(commentService.getFullCommentsOfPost(id), "Got all comments of this posts :')"), HttpStatus.OK);
	}

}
