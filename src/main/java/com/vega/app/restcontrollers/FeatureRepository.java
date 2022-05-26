package com.vega.app.restcontrollers;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vega.app.dtos.FeatureDTO;
import com.vega.app.dtos.simple.SimpleFeatureDTO;
import com.vega.app.entities.Feature;

public interface FeatureRepository extends JpaRepository<Feature, Long> {

	@Query("SELECT new com.vega.app.dtos.simple.SimpleFeatureDTO(f.id, f.name, f.nbrAvailable) FROM Feature f WHERE f.stock.id = ?1 and f.deleted is false")
	Set<SimpleFeatureDTO> findByStockIdAndDeletedFalse(Long id);
	
	@Query("SELECT new com.vega.app.dtos.simple.SimpleFeatureDTO(f.id, f.name, f.nbrAvailable) FROM Feature f WHERE f.id = ?1 and f.deleted is false")
	SimpleFeatureDTO findSimpleDTOById(Long id);
	
	@Query("SELECT new com.vega.app.dtos.FeatureDTO(f.id, f.deleted, f.createdAt, f.modifiedAt, f.name, f.nbrAvailable, f.stock.id, f.stock.name) FROM Feature f WHERE f.id = ?1 and f.deleted is false")
	FeatureDTO findDTOById(Long id);

}
