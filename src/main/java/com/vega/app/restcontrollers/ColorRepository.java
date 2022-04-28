package com.vega.app.restcontrollers;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vega.app.dtos.simple.SimpleColorDTO;
import com.vega.app.entities.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {

	@Query("SELECT new com.vega.app.dtos.simple.SimpleColorDTO(c.id, c.name, c.available) FROM Color c WHERE c.feature.id = ?1")
	Set<SimpleColorDTO> findByStockId(Long id);
}
