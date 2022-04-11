package com.vega.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vega.app.dtos.PostDTO;
import com.vega.app.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	Post getById(Long id);
	
	@Query("SELECT new com.vega.app.dtos.PostDTO(p.id, p.deleted, p.createdAt, p.modifiedAt, p.title, p.content, c.id, c.name, p.numberSeen, p.nbrClicks, p.nbrComments, p.nbrReactions) from Post p LEFT JOIN Category c WHERE p.id = ?1 ORDER BY p.createdAt DESC")
	PostDTO findDTOById(Long id);

	@Query("SELECT new com.vega.app.dtos.PostDTO(p.id, p.deleted, p.createdAt, p.modifiedAt, p.title, p.content, p.category.id, p.category.name, p.user.id, p.user.firstName, p.user.lastName, p.numberSeen, p.nbrClicks, p.nbrComments, p.nbrReactions) from Post p WHERE p.deleted = ?1 ")
	Page<PostDTO> getAll(Boolean deleted,Pageable page);

	@Query("SELECT new com.vega.app.dtos.PostDTO(p.id, p.deleted, p.createdAt, p.modifiedAt, p.title, p.content, c.id, c.name, p.numberSeen, p.nbrClicks, p.nbrComments, p.nbrReactions) from Post p LEFT JOIN Category c WHERE c.id = ?1 AND p.deleted is false ORDER BY p.createdAt DESC")
	Page<PostDTO> findPostsByCategoryId(Long id, Pageable page);

	@Query("SELECT new com.vega.app.dtos.PostDTO(p.id, p.deleted, p.createdAt, p.modifiedAt, p.title, p.content, p.category.id, p.category.name, p.numberSeen, p.nbrClicks, p.nbrComments, p.nbrReactions) from Post p LEFT JOIN p.user u WHERE u.id = ?1 AND p.deleted = ?2 ")
	Page<PostDTO> findByOnwerId(Long ownerId, Boolean deleted, Pageable page);
	
}
