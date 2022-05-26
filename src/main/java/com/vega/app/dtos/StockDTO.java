package com.vega.app.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vega.app.dtos.simple.SimpleFeatureDTO;
import com.vega.app.dtos.simple.SimpleInvoiceDTO;
import com.vega.app.dtos.simple.SimpleProductDTO;
import com.vega.app.dtos.simple.SimpleStockDTO;
import com.vega.app.dtos.simple.SimpleTagDTO;
import com.vega.app.dtos.simple.SimpleUserDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StockDTO extends SimpleStockDTO {

	private String name;

	private String description;

	private Integer initialQuantity;

	private Integer quantity;

	private Double unitPrice;

	private Double sellingPrice;

	private Double total;

	private SimpleUserDTO provider;

	private Set<SimpleInvoiceDTO> invoices = new HashSet<>();

	private Set<SimpleProductDTO> products = new HashSet<>();

	private Set<SimpleFeatureDTO> features = new HashSet<>();

	private Set<SimpleTagDTO> tags = new HashSet<>();

	public StockDTO(Long id, Boolean deleted, Date createdAt, Date modifiedAt, String name, String description,
			Integer initialQuantity, Integer quantity, Double unitPrice, Double sellingPrice, Double total, Long providerId,
			String providerFirstName, String providerLastName) {
		super(id, deleted, createdAt, modifiedAt);
		this.name = name;
		this.description = description;
		this.initialQuantity = initialQuantity;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.sellingPrice = sellingPrice;
		this.total = total;
		this.provider = new SimpleUserDTO(providerId, providerFirstName, providerLastName);
	}

}
