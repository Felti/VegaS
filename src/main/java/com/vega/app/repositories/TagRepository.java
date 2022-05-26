package com.vega.app.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vega.app.dtos.simple.SimpleTagDTO;
import com.vega.app.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{
	
	int countByNameIgnoreCaseAndCategoryId(String name,Long categoryId); 
	
	@Query("SELECT new com.vega.app.dtos.simple.SimpleTagDTO(t.id, t.name) FROM Tag t LEFT JOIN t.stocks s WHERE s.id = ?1")
	Set<SimpleTagDTO> findTagsOfStock(Long stock); 
	
	@Query("SELECT new com.vega.app.dtos.simple.SimpleTagDTO(t.id, t.deleted, t.createdAt, t.modifiedAt, t.name) FROM Tag t WHERE t.deleted = false")
	Set<SimpleTagDTO> findAllAndDeletedFalse(); 

}
