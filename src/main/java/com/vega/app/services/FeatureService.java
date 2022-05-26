package com.vega.app.services;

import java.util.Set;

import com.vega.app.dtos.FeatureDTO;
import com.vega.app.dtos.simple.SimpleFeatureDTO;
import com.vega.app.entities.Feature;
import com.vega.app.entities.Stock;

public interface FeatureService {

	// adds features to category and return a the total size of stocks
	int addFeaturesToStock(Set<SimpleFeatureDTO> features, Stock stock);

	Set<SimpleFeatureDTO> getFeaturesOfStock(Long stockId);

	void updateFeatureNbrAvailable(Long id);
	
	SimpleFeatureDTO getSimpleDTOById(Long id);

	void save(Feature feature);

	FeatureDTO mapEntityToDTO(Feature feature);

	Feature mapDTOToEntity(FeatureDTO featureDTO);

	SimpleFeatureDTO mapEntityToSimpleDTO(Feature feature);

	Feature mapSimpleDTOToEntity(SimpleFeatureDTO featureDTO);

	FeatureDTO getDTOById(Long id);
	


}
