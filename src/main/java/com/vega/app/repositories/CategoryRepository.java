package com.vega.app.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vega.app.dtos.CategoryDTO;
import com.vega.app.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query("SELECT new com.vega.app.dtos.CategoryDTO(c.id,c.name) FROM Category c Where c.deleted = ?1 ORDER BY c.name ")
	Set<CategoryDTO> getAll(Boolean deleted);
}

