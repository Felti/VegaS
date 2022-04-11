package com.vega.app.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vega.app.dtos.CommentDTO;
import com.vega.app.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	Optional<Comment> findById(Long id);

	@Query("SELECT new com.vega.app.dtos.CommentDTO(c.id, c.content) FROM Comment c WHERE c.id = ?1 AND c.deleted is false")
	CommentDTO findDTObyId(Long id);

	@Query("SELECT new com.vega.app.dtos.CommentDTO(c.id, c.createdAt, c.content) FROM Comment c WHERE c.post.id = ?1 AND c.deleted is false")
	Set<CommentDTO> findSimpleCommentsOfPost(Long id);
	
	@Query("SELECT new com.vega.app.dtos.CommentDTO(c.id, c.createdAt, c.content) FROM Comment c WHERE c.post.id = ?1 AND c.deleted is false")
	Set<CommentDTO> findFullCommentsOfPost(Long id);

	@Query("SELECT count(*) FROM Comment c WHERE c.post.id = ?1 AND c.deleted is false")
	Long countCommentsOfPost(Long id);

}
