package com.vega.app.services;


import com.vega.app.dtos.ProductDTO;
import com.vega.app.entities.Product;

public interface ProductService {

	Product getById(Long id);

	ProductDTO getDTOById(Long id);

	ProductDTO create(ProductDTO productDTO);

	ProductDTO edit(ProductDTO productDTO);

	ProductDTO softDelete(Long id);

	ProductDTO undelete(Long id);

	void delete(Long id);

	ProductDTO mapEntityToDTO(Product product);

	Product mapDTOToEntity(ProductDTO dto);

}
