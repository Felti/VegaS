package com.vega.app.services.impl;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vega.app.dtos.ColorDTO;
import com.vega.app.dtos.simple.SimpleColorDTO;
import com.vega.app.entities.Color;
import com.vega.app.entities.Feature;
import com.vega.app.restcontrollers.ColorRepository;
import com.vega.app.services.ColorService;

@Service
public class ColorServiceImpl implements ColorService {

	@Autowired
	private ColorRepository colorRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Set<SimpleColorDTO> getByFeatureId(Long id) {
		return colorRepository.findByStockId(id);
	}

	@Override
	public int addColorsToFeature(Set<SimpleColorDTO> colors, Feature feature) {
		var totalElements = 0;
		if (!CollectionUtils.isEmpty(colors)) {

			for (SimpleColorDTO color : colors) {
				var clr = new Color();
				clr.setName(color.getName());
				clr.setAvailable(color.getAvailable());
				clr.setFeature(feature);

				colorRepository.save(clr);

				totalElements += clr.getAvailable();
			}
		}
		return totalElements;
	}

	@Override
	public void save(Color color) {
		colorRepository.save(color);
	}

	@Override
	public Color mapDTOToEntity(ColorDTO colorDTO) {
		return mapper.map(colorDTO, Color.class);
	}

	@Override
	public ColorDTO mapEntityToDTO(Color color) {
		return mapper.map(color, ColorDTO.class);
	}

	@Override
	public SimpleColorDTO mapEntityToSimpleDTO(Color color) {
		return mapper.map(color, SimpleColorDTO.class);
	}

	@Override
	public Color mapSimpleDTOToEntity(SimpleColorDTO colorDTO) {
		return mapper.map(colorDTO, Color.class);
	}

}
