package com.vega.app.services;

import org.springframework.data.domain.Page;

import com.vega.app.dtos.DocumentRequest;
import com.vega.app.dtos.PageableDTO;
import com.vega.app.dtos.PostDTO;
import com.vega.app.entities.Post;

public interface PostService {

	Page<PostDTO> getAll(DocumentRequest request);

	Page<PostDTO> getPostsByCategoryId(Long categoryId, PageableDTO page);

	Post getById(Long id);

	PostDTO getDTOById(Long id);

	PostDTO create(PostDTO postDTO);

	PostDTO edit(PostDTO postDTO);

	PostDTO softDelete(Long id);

	PostDTO undelete(Long id);

	void delete(Long id);

	PostDTO mapEntityToDTO(Post post);

	Post mapDTOToEntity(PostDTO dto);

}
