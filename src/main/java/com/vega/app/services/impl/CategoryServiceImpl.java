package com.vega.app.services.impl;

import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.vega.app.constants.ErrorMessages;
import com.vega.app.dtos.CategoryDTO;
import com.vega.app.dtos.simple.SimpleCategoryDTO;
import com.vega.app.entities.Category;
import com.vega.app.repositories.CategoryRepository;
import com.vega.app.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	ModelMapper mapper;

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Set<CategoryDTO> getAll(Boolean deleted) {
		return categoryRepository.getAll(deleted);
	}

	@Override
	public Category getById(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		Optional<Category> category = categoryRepository.findById(id);
		
		if (category.isPresent()) return category.get();
		
		return null;

	}

	@Override
	public CategoryDTO create(CategoryDTO categoryDTO) {
		Assert.notNull(categoryDTO, ErrorMessages.OBJECT_NOT_FOUND);

		var category = new Category();

		category.setName(categoryDTO.getName().trim());

		return mapEntityToDTO(categoryRepository.save(category));

	}

	@Override
	public CategoryDTO edit(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public SimpleCategoryDTO mapEntityToSimpleDTO(Category category) {
		return mapper.map(category, SimpleCategoryDTO.class);
	}

	@Override
	public Category mapSimpleDTOToEntity(SimpleCategoryDTO categoryDTO) {
		return mapper.map(categoryDTO, Category.class);
	}

	@Override
	public CategoryDTO mapEntityToDTO(Category category) {
		return mapper.map(category, CategoryDTO.class);
	}

	@Override
	public Category mapDTOToEntity(CategoryDTO categoryDTO) {
		return mapper.map(categoryDTO, Category.class);
	}

}
