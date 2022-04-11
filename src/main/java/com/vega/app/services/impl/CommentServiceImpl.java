package com.vega.app.services.impl;

import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.vega.app.constants.ErrorMessages;
import com.vega.app.dtos.CommentDTO;
import com.vega.app.entities.Comment;
import com.vega.app.repositories.CommentRepository;
import com.vega.app.services.CommentService;
import com.vega.app.services.PostService;
import com.vega.app.services.UserService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	UserService userService;

	@Autowired
	PostService postService;

	@Autowired
	ModelMapper mapper;

	@Override
	public Comment getById(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		Optional<Comment> comment = commentRepository.findById(id);

		if (comment.isPresent()) return comment.get();
		return null;
	}

	@Override
	public CommentDTO getDTOById(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		return commentRepository.findDTObyId(id);
	}

	@Override
	public Set<CommentDTO> getSimpleCommentsOfPost(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		return commentRepository.findSimpleCommentsOfPost(id);
	}
	
	@Override
	public Set<CommentDTO> getFullCommentsOfPost(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		return commentRepository.findFullCommentsOfPost(id);
	}

	@Override
	public CommentDTO create(CommentDTO commentDto) {
		Assert.notNull(commentDto, ErrorMessages.OBJECT_NOT_FOUND);
		Assert.notNull(commentDto.getContent(), "Content " + ErrorMessages.OBJECT_NOT_FOUND);
		Assert.notNull(commentDto.getPost().getId(), ErrorMessages.ID_NOT_FOUND);
		Assert.notNull(commentDto.getUser().getId(), ErrorMessages.ID_NOT_FOUND);

		var comment = new Comment();

		comment.setContent(commentDto.getContent());
		comment.setPost(postService.mapDTOToEntity(postService.getDTOById(commentDto.getPost().getId())));
		comment.setUser(userService.mapSimpleDTOToEntity(userService.getDTOBasicById(commentDto.getUser().getId())));
		

		return mapEntityToDTO(commentRepository.save(comment));
	}

	@Override
	public CommentDTO edit(CommentDTO commentDto) {
		Assert.notNull(commentDto, ErrorMessages.OBJECT_NOT_FOUND);
		Assert.notNull(commentDto.getContent(), "Content " + ErrorMessages.OBJECT_NOT_FOUND);
		Assert.notNull(commentDto.getId(), ErrorMessages.ID_NOT_FOUND);

		var comment = mapDTOToEntity(commentRepository.findDTObyId(commentDto.getId()));

		comment.setContent(commentDto.getContent());

		return mapEntityToDTO(commentRepository.save(comment));
	}

	@Override
	public CommentDTO softDelete(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		var comment = commentRepository.getById(id);
		Assert.notNull(comment, ErrorMessages.OBJECT_NOT_FOUND);
		comment.setDeleted(true);
		return mapEntityToDTO(commentRepository.save(comment));
	}

	@Override
	public CommentDTO undelete(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		var comment = commentRepository.getById(id);

		Assert.notNull(comment, ErrorMessages.OBJECT_NOT_FOUND);
		comment.setDeleted(false);

		return mapEntityToDTO(commentRepository.save(comment));
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Long countCommentsOfPost(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		return commentRepository.countCommentsOfPost(id);
	}

	@Override
	public CommentDTO mapEntityToDTO(Comment post) {
		return mapper.map(post, CommentDTO.class);

	}

	@Override
	public Comment mapDTOToEntity(CommentDTO dto) {
		return mapper.map(dto, Comment.class);

	}


}
