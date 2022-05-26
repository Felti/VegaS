package com.vega.app.services.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.vega.app.dtos.FeatureDTO;
import com.vega.app.dtos.simple.SimpleColorDTO;
import com.vega.app.dtos.simple.SimpleFeatureDTO;

import com.vega.app.entities.Feature;
import com.vega.app.entities.Stock;
import com.vega.app.restcontrollers.FeatureRepository;
import com.vega.app.services.ColorService;
import com.vega.app.services.FeatureService;
import com.vega.app.services.StockService;

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
	
	@Autowired
	StockService stockService;

	@Override
	public Set<SimpleFeatureDTO> getFeaturesOfStock(Long stockId) {
		return featureRepository.findByStockIdAndDeletedFalse(stockId);
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

				ft.setColors(colorService.getColorsOfFeature(ft.getId()).parallelStream()
						.map(f -> colorService.mapSimpleDTOToEntity(f)).collect(Collectors.toSet()));
				totalElements += ft.getNbrAvailable();
			}

		}

		return totalElements;
	}

	@Override
	public void updateFeatureNbrAvailable(Long id) {
		Assert.notNull(id, "updateFeatureNbrAvailable : id cannot be null");
		FeatureDTO feature = getDTOById(id);
		if (feature != null) {
			var totalavailable = 0;

			for (SimpleColorDTO color : colorService.getColorsOfFeature(id)) {
				totalavailable += color.getAvailable();
			}

			feature.setNbrAvailable(totalavailable);

			featureRepository.save(mapDTOToEntity(feature));
			
			stockService.updateStockQuantity(feature.getStock().getId());
		} else {
			throw new NullPointerException("updateFeatureNbrAvailable : feature is null ");
		}

	}

	@Override
	public SimpleFeatureDTO getSimpleDTOById(Long id) {
		return featureRepository.findSimpleDTOById(id);
	}

	@Override
	public FeatureDTO getDTOById(Long id) {
		return featureRepository.findDTOById(id);
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
