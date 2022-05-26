package com.vega.app.services.impl;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.vega.app.constants.ErrorMessages;
import com.vega.app.dtos.ProductDTO;
import com.vega.app.dtos.simple.SimpleProductDTO;
import com.vega.app.entities.Order;
import com.vega.app.entities.Product;
import com.vega.app.entities.User;
import com.vega.app.repositories.ProductRepository;
import com.vega.app.services.ColorService;
import com.vega.app.services.ProductService;
import com.vega.app.services.StockService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StockService stockService;

	@Autowired
	private ColorService colorService;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Set<SimpleProductDTO> getProductsOfOrder(Long orderId) {
		return productRepository.findProductsOfOrder(orderId);
	}

	@Override
	public Product getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDTO getDTOById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimpleProductDTO createFromOrder(SimpleProductDTO productDTO, Order order, User customer) {
		Assert.notNull(order, ErrorMessages.OBJECT_NOT_FOUND);
		Assert.notNull(productDTO.getStockId(), "createFromOrder : stock id cannot be null");
		Assert.notNull(productDTO.getSize(), "createFromOrder : size id cannot be null");
		Assert.notNull(productDTO.getColor(), "createFromOrder : size id cannot be null");

		var product = new Product();
		product.setName(product.getName());
		product.setColor(productDTO.getColor().getName());
		product.setSize(productDTO.getSize().getName());
		product.setPrice(productDTO.getPrice());
		product.setOrder(order);

		// set product stock
		product.setStock(stockService.mapSimpleDTOToEntity(stockService.getSimpleDTOById(productDTO.getStockId())));
		
		// set product customer
		product.setCustomer(customer);

		// Trigger removal item sold from stock
		colorService.removeUnitSold(productDTO.getColor().getId());

		return mapEntityToDTO(save(product));
	}

	@Override
	public ProductDTO edit(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDTO softDelete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDTO undelete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public ProductDTO mapEntityToDTO(Product product) {
		return mapper.map(product, ProductDTO.class);
	}

	@Override
	public Product mapDTOToEntity(ProductDTO dto) {
		return mapper.map(dto, Product.class);
	}

	@Override
	public SimpleProductDTO mapEntityToSimpleDTO(Product product) {
		return mapper.map(product, SimpleProductDTO.class);
	}

	@Override
	public Product mapSimpleDTOToEntity(SimpleProductDTO dto) {
		return mapper.map(dto, Product.class);
	}

	@Override
	public ProductDTO create(SimpleProductDTO productDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
