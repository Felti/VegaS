package com.vega.app.restcontrollers;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vega.app.dtos.simple.SimpleFeatureDTO;
import com.vega.app.entities.Feature;

public interface FeatureRepository extends JpaRepository<Feature, Long> {

	@Query("SELECT new com.vega.app.dtos.simple.SimpleFeatureDTO(f.id, f.name, f.nbrAvailable) FROM Feature f WHERE f.stock.id = ?1")
	Set<SimpleFeatureDTO> findByStockId(Long id);

}
