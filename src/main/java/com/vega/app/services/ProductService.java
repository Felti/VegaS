package com.vega.app.services;


import com.vega.app.dtos.ProductDTO;
import com.vega.app.dtos.simple.SimpleProductDTO;
import com.vega.app.entities.Order;
import com.vega.app.entities.Product;
import com.vega.app.entities.User;

import java.util.Set;

public interface ProductService {

	Product getById(Long id);

	ProductDTO getDTOById(Long id);

	ProductDTO create(SimpleProductDTO productDTO);

	ProductDTO edit(ProductDTO productDTO);

	ProductDTO softDelete(Long id);

	ProductDTO undelete(Long id);

	ProductDTO mapEntityToDTO(Product product);

	Product mapDTOToEntity(ProductDTO dto);
	
	SimpleProductDTO mapEntityToSimpleDTO(Product product);

	Product mapSimpleDTOToEntity(SimpleProductDTO dto);

	Product save(Product product);

	SimpleProductDTO createFromOrder(SimpleProductDTO productDTO, Order order, User customer);
	
	Set<SimpleProductDTO> getProductsOfOrder(Long orderId);

}
