package com.vega.app.services.impl;

import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.vega.app.constants.ErrorMessages;
import com.vega.app.dtos.TagDTO;
import com.vega.app.dtos.simple.SimpleTagDTO;
import com.vega.app.entities.Tag;
import com.vega.app.exceptions.ObjectAlreadyExists;
import com.vega.app.repositories.TagRepository;
import com.vega.app.services.CategoryService;
import com.vega.app.services.TagService;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	TagRepository tagRepository;

	@Autowired
	CategoryService categoryService;

	@Override
	public TagDTO create(TagDTO dto) {
		Assert.notNull(dto, ErrorMessages.OBJECT_NOT_FOUND);

		int alreadyExistCouple = tagRepository.countByNameIgnoreCaseAndCategoryId(dto.getName(), dto.getCategory().getId());

		if (alreadyExistCouple == 0) {
			if (ObjectUtils.isNotEmpty(dto.getName()) && ObjectUtils.isNotEmpty(dto.getName().trim())
					&& ObjectUtils.isNotEmpty(dto.getCategory())) {

				var category = categoryService.getById(dto.getCategory().getId());
				var tag = new Tag();

				tag.setName(dto.getName().trim());
				tag.setCategory(category);

				return mapEntityToDTO(save(tag));
			} else throw new NullPointerException("La clé ou la valeur sont manquantes.");
		} else throw new ObjectAlreadyExists(Tag.class.getSimpleName());
	}

	@Override
	public Set<SimpleTagDTO> getTagsOfStock(Long StockId) {
		Assert.notNull(StockId, ErrorMessages.ID_NOT_FOUND);
		return tagRepository.findTagsOfStock(StockId);

	}

	@Override
	public TagDTO mapEntityToDTO(Tag tag) {
		return mapper.map(tag, TagDTO.class);
	}

	@Override
	public Tag save(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public Tag mapDTOToEntity(TagDTO dto) {
		return mapper.map(dto, Tag.class);
	}

	@Override
	public SimpleTagDTO mapEntityToSimpleDTO(Tag tag) {
		return mapper.map(tag, SimpleTagDTO.class);
	}

	@Override
	public Tag mapSimpleDTOToEntity(SimpleTagDTO simpleUser) {
		return mapper.map(simpleUser, Tag.class);
	}

}
