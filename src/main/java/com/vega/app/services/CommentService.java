package com.vega.app.services;

import java.util.Set;

import com.vega.app.dtos.CommentDTO;
import com.vega.app.entities.Comment;

public interface CommentService {

	Set<CommentDTO> getSimpleCommentsOfPost(Long id);
	
	Set<CommentDTO> getFullCommentsOfPost(Long id);

	Long countCommentsOfPost(Long id);

	Comment getById(Long id);

	CommentDTO getDTOById(Long id);

	CommentDTO create(CommentDTO comment);

	CommentDTO edit(CommentDTO comment);

	CommentDTO softDelete(Long id);

	CommentDTO undelete(Long id);

	void delete(Long id);

	CommentDTO mapEntityToDTO(Comment comment);

	Comment mapDTOToEntity(CommentDTO dto);
}
