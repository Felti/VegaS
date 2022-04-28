package com.vega.app.services;

import java.util.Set;

import com.vega.app.dtos.ColorDTO;
import com.vega.app.dtos.simple.SimpleColorDTO;
import com.vega.app.entities.Color;
import com.vega.app.entities.Feature;

public interface ColorService {

	Set<SimpleColorDTO> getByFeatureId(Long id);

	int addColorsToFeature(Set<SimpleColorDTO> colors, Feature feature);

	void save(Color color);

	Color mapDTOToEntity(ColorDTO colorDTO);

	ColorDTO mapEntityToDTO(Color color);

	SimpleColorDTO mapEntityToSimpleDTO(Color color);

	Color mapSimpleDTOToEntity(SimpleColorDTO colorDTO);

}
