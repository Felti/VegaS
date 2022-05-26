package com.vega.app.services.impl;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.vega.app.constants.ErrorMessages;
import com.vega.app.dtos.ColorDTO;
import com.vega.app.dtos.simple.SimpleColorDTO;
import com.vega.app.entities.Color;
import com.vega.app.entities.Feature;
import com.vega.app.repositories.ColorRepository;
import com.vega.app.services.ColorService;
import com.vega.app.services.FeatureService;

@Service
public class ColorServiceImpl implements ColorService {

	@Autowired
	private ColorRepository colorRepository;

	@Autowired
	private FeatureService featureService;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Set<SimpleColorDTO> getColorsOfFeature(Long id) {
		return colorRepository.findByColorsOfFeature(id);
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
	public void removeUnitSold(Long colorId) {
		Assert.notNull(colorId, "removeUnitSolde : colorId cannot be null");

		// update color nbr unites
		ColorDTO color = getDTOById(colorId);
		if (color != null) {

			color.setAvailable(color.getAvailable() - 1);
			colorRepository.save(mapDTOToEntity(color));

			// update total features
			featureService.updateFeatureNbrAvailable(color.getFeature().getId());
		} else {
			throw new NullPointerException("removeUnitSolde : Couldn't find color");
		}

	}

	@Override
	public ColorDTO getDTOById(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		return colorRepository.findDTOById(id);
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

	@Override
	public SimpleColorDTO getSimpleDTOById(Long colorId) {
		return colorRepository.findSimpleDTOById(colorId);
	}

}
