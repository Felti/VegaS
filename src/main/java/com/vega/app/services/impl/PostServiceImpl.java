package com.vega.app.services.impl;

import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.vega.app.constants.ErrorMessages;
import com.vega.app.dtos.DocumentRequest;
import com.vega.app.dtos.PageableDTO;
import com.vega.app.dtos.PostDTO;
import com.vega.app.entities.Post;
import com.vega.app.repositories.PostRepository;
import com.vega.app.services.CategoryService;
import com.vega.app.services.CommentService;
import com.vega.app.services.PostService;
import com.vega.app.services.UserService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	CategoryService categoryService;

	@Autowired
	UserService userService;

	@Autowired
	CommentService commentService;

	@Autowired
	ModelMapper mapper;

	@Override
	public Page<PostDTO> getAll(DocumentRequest request) {
		Assert.notNull(request.getPageable().getPage(), "Number of pages cannot be null");
		Assert.notNull(request.getPageable().getSize(), "Size of page cannot be null");

		Pageable pagerequest = PageRequest.of(request.getPageable().getPage(), request.getPageable().getSize());

		if (Boolean.TRUE.equals(ObjectUtils.isNotEmpty(request.getIdOwner()))
				&& Boolean.TRUE.equals(request.getIsOwner())) {
			return getOwnerPosts(request.getIdOwner(), request.getDeleted(), pagerequest);
		}

		return getPosts(request.getDeleted(), pagerequest);
	}

	public Page<PostDTO> getOwnerPosts(Long id, Boolean deleted, Pageable page) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		Page<PostDTO> posts = postRepository.findByOnwerId(id, deleted, page);
		posts.getContent().parallelStream().forEach(p -> p.setNbrComments(commentService.countCommentsOfPost(p.getId())));
		return posts;
	}

	public Page<PostDTO> getPosts(Boolean deleted, Pageable page) {
		Page<PostDTO> posts = postRepository.getAll(deleted, page);
		posts.getContent().parallelStream().forEach(p -> p.setNbrComments(commentService.countCommentsOfPost(p.getId())));
		return posts;
	}

	@Override
	public Page<PostDTO> getPostsByCategoryId(Long id, PageableDTO page) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		Assert.notNull(page.getPage(), "Number of pages cannot be null");
		Assert.notNull(page.getSize(), "Size of page cannot be null");
		Pageable pageReaquest = PageRequest.of(page.getPage() - 1, page.getSize());
		return postRepository.findPostsByCategoryId(id, pageReaquest);
	}

	@Override
	public Post getById(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		Optional<Post> post = postRepository.findById(id);

		if (post.isPresent()) return post.get();

		return null;
	}
	
	@Override
	public PostDTO getDTOById(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		return postRepository.findDTOById(id);
	}

	@Override
	public PostDTO create(PostDTO postDTO) {
		Assert.notNull(postDTO, ErrorMessages.OBJECT_NOT_FOUND);

		var post = new Post();

		post.setTitle(postDTO.getTitle().trim());
		post.setContent(postDTO.getContent().trim());

		Assert.notNull(postDTO.getCategory(), "Category missing");

		post.setCategory(categoryService.mapSimpleDTOToEntity(postDTO.getCategory()));

		Assert.notNull(postDTO.getUser(), "User missing");

		post.setUser(userService.mapSimpleDTOToEntity(postDTO.getUser()));

		return mapEntityToDTO(postRepository.save(post));
	}

	@Override
	public PostDTO edit(PostDTO postDTO) {
		Assert.notNull(postDTO.getId(), ErrorMessages.ID_NOT_FOUND);
		var post = getById(postDTO.getId());

		Assert.notNull(post, ErrorMessages.OBJECT_NOT_FOUND);

		if (ObjectUtils.isNotEmpty(postDTO.getTitle())) {
			post.setTitle(postDTO.getTitle().trim());
		}

		if (ObjectUtils.isNotEmpty(postDTO.getContent())) {
			post.setTitle(postDTO.getContent().trim());
		}

		Assert.notNull(postDTO.getCategory(), "Please Enter a category");
		if (ObjectUtils.isNotEmpty(postDTO.getCategory())) {
			post.setCategory(categoryService.mapSimpleDTOToEntity(postDTO.getCategory()));
		}

		return mapEntityToDTO(postRepository.save(post));
	}

	@Override
	public PostDTO softDelete(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		var post = postRepository.getById(id);

		Assert.notNull(post, ErrorMessages.OBJECT_NOT_FOUND);
		post.setDeleted(true);

		return mapEntityToDTO(postRepository.save(post));
	}

	@Override
	public PostDTO undelete(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		var post = postRepository.getById(id);

		Assert.notNull(post, ErrorMessages.OBJECT_NOT_FOUND);
		post.setDeleted(false);

		return mapEntityToDTO(postRepository.save(post));
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public PostDTO mapEntityToDTO(Post post) {
		return mapper.map(post, PostDTO.class);

	}

	@Override
	public Post mapDTOToEntity(PostDTO dto) {
		return mapper.map(dto, Post.class);

	}



}
