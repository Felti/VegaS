package com.vega.app.services;

import java.util.Set;

import com.vega.app.dtos.TagDTO;
import com.vega.app.dtos.simple.SimpleTagDTO;
import com.vega.app.entities.Tag;


public interface TagService {
	
	TagDTO mapEntityToDTO(Tag tag);

	Tag mapDTOToEntity(TagDTO dto);

	TagDTO create(TagDTO dto);

	Tag save(Tag tag);

	Tag mapSimpleDTOToEntity(SimpleTagDTO tagDTO);

	SimpleTagDTO mapEntityToSimpleDTO(Tag tag);

	Set<SimpleTagDTO> getTagsOfStock(Long StockId);

}
