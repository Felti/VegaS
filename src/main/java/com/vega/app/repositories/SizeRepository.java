package com.vega.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vega.app.entities.Feature;

public interface SizeRepository extends JpaRepository<Feature,Long>{

}
