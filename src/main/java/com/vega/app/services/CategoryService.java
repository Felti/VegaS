package com.vega.app.services;

import java.util.Set;

import com.vega.app.dtos.CategoryDTO;
import com.vega.app.dtos.simple.SimpleCategoryDTO;
import com.vega.app.entities.Category;

public interface CategoryService {

	Set<CategoryDTO> getAll(Boolean deleted);

	Category getById(Long id);
	
	CategoryDTO create(CategoryDTO postDTO);
	
	CategoryDTO edit(Long id);
	
	void delete(Long id);
	
	SimpleCategoryDTO mapEntityToSimpleDTO(Category category);
	
	Category mapSimpleDTOToEntity(SimpleCategoryDTO categoryDTO);

	CategoryDTO mapEntityToDTO(Category category);

	Category mapDTOToEntity(CategoryDTO categoryDTO);
	
}
