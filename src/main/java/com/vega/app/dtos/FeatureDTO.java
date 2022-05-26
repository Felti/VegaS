package com.vega.app.dtos;

import java.util.Date;

import com.vega.app.dtos.simple.SimpleFeatureDTO;
import com.vega.app.dtos.simple.SimpleStockDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FeatureDTO extends SimpleFeatureDTO {

	private SimpleStockDTO stock;

	public FeatureDTO(Long id, String name, Integer nbrAvailable) {
		super(id, name, nbrAvailable);

	}

	public FeatureDTO(Long id, Boolean deleted, Date createdAt, Date modifiedAt, String name, Integer nbrAvailable,
			Long stockId, String stockName) {
		super(id, deleted, createdAt, modifiedAt, name, nbrAvailable);
		this.stock = new SimpleStockDTO(stockId, stockName);

	}

}
