package com.vega.app.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vega.app.dtos.ColorDTO;
import com.vega.app.dtos.simple.SimpleColorDTO;
import com.vega.app.entities.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {

	@Query("SELECT new com.vega.app.dtos.simple.SimpleColorDTO(c.id, c.name, c.available) FROM Color c WHERE c.feature.id = ?1 ")
	Set<SimpleColorDTO> findByColorsOfFeature(Long id);

	@Query("SELECT new com.vega.app.dtos.ColorDTO(c.id, c.deleted, c.createdAt, c.modifiedAt, c.name, c.available, c.feature.id, c.feature.name) FROM Color c WHERE c.id = ?1 AND c.deleted = false ")
	ColorDTO findDTOById(Long id);

	@Query("SELECT new com.vega.app.dtos.simple.SimpleColorDTO(c.id, c.name, c.available) FROM Color c WHERE c.id = ?1 ")
	SimpleColorDTO findSimpleDTOById(Long color);
}
