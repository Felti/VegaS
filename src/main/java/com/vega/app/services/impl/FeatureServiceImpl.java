package com.vega.app.services.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vega.app.dtos.FeatureDTO;

import com.vega.app.dtos.simple.SimpleFeatureDTO;

import com.vega.app.entities.Feature;
import com.vega.app.entities.Stock;
import com.vega.app.restcontrollers.FeatureRepository;
import com.vega.app.services.ColorService;
import com.vega.app.services.FeatureService;

@Service
public class FeatureServiceImpl implements FeatureService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	FeatureService featureService;

	@Autowired
	FeatureRepository featureRepository;

	@Autowired
	ColorService colorService;

	@Override
	public Set<SimpleFeatureDTO> getByStockId(Long stockId) {
		return featureRepository.findByStockId(stockId);
	}

	@Override
	public int addFeaturesToStock(Set<SimpleFeatureDTO> features, Stock stock) {
		var totalElements = 0;
		if (!CollectionUtils.isEmpty(features)) {

			for (SimpleFeatureDTO feature : features) {
				var ft = new Feature();
				ft.setName(feature.getName());
				ft.setStock(stock);
				ft.setNbrAvailable(null);
				featureRepository.save(ft);

				ft.setNbrAvailable(colorService.addColorsToFeature(feature.getColors(), ft));

				ft.setColors(colorService.getByFeatureId(ft.getId()).parallelStream()
						.map(f -> colorService.mapSimpleDTOToEntity(f)).collect(Collectors.toSet()));
				totalElements += ft.getNbrAvailable();
			}

		}

		return totalElements;
	}

	@Override
	public void save(Feature feature) {
		System.out.println("called save");
		featureService.save(feature);

	}

	@Override
	public Feature mapDTOToEntity(FeatureDTO featureDTO) {
		return mapper.map(featureDTO, Feature.class);
	}

	@Override
	public FeatureDTO mapEntityToDTO(Feature feature) {
		return mapper.map(feature, FeatureDTO.class);
	}

	@Override
	public SimpleFeatureDTO mapEntityToSimpleDTO(Feature feature) {
		return mapper.map(feature, FeatureDTO.class);
	}

	@Override
	public Feature mapSimpleDTOToEntity(SimpleFeatureDTO featureDTO) {
		return mapper.map(featureDTO, Feature.class);
	}

}
