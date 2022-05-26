package com.vega.app.dtos;

import java.util.Date;

import com.vega.app.dtos.simple.SimpleColorDTO;
import com.vega.app.dtos.simple.SimpleFeatureDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ColorDTO extends SimpleColorDTO {

	private SimpleFeatureDTO feature;

	public ColorDTO(Long id, String name, Integer available, Long featureId, String featureName) {
		super(id, name, available);
		this.feature = new SimpleFeatureDTO(featureId, featureName);
	}

	public ColorDTO(Long id, Boolean deleted, Date createdAt, Date modifiedAt, String name, Integer available,
			Long featureId, String featureName) {
		super(id, deleted, createdAt, modifiedAt, name, available);
		this.feature = new SimpleFeatureDTO(featureId, featureName);
	}

}
