package com.vega.app.services.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.vega.app.constants.ErrorMessages;
import com.vega.app.dtos.OrderDTO;
import com.vega.app.dtos.PageableDTO;
import com.vega.app.dtos.simple.SimpleOrderDTO;
import com.vega.app.dtos.simple.SimpleProductDTO;
import com.vega.app.entities.Order;
import com.vega.app.entities.Product;
import com.vega.app.exceptions.ObjectAlreadyExists;
import com.vega.app.repositories.OrderRepository;
import com.vega.app.services.OrderService;
import com.vega.app.services.ProductService;
import com.vega.app.services.UserService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Page<OrderDTO> getAll(PageableDTO page, Boolean deleted) {
		Assert.notNull(page.getPage(), ErrorMessages.PAGE_NUMBER_MISSING);
		Assert.notNull(page.getSize(), ErrorMessages.PAGE_SIZE_MISSING);
		Assert.notNull(deleted, "deleted " + ErrorMessages.OBJECT_NOT_FOUND);

		// Create page request
		Pageable pageRequest = PageRequest.of(page.getPage() - 1, page.getSize());

		Page<OrderDTO> orders = orderRepository.findAllByDeleted(deleted, pageRequest);
		orders.getContent().parallelStream()
				.forEach(order -> order.setProducts(productService.getProductsOfOrder(order.getId())));
		return orders;
	}

	@Override
	public Order getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO getDTOById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO create(OrderDTO orderDTO) {
		Assert.notNull(orderDTO, ErrorMessages.OBJECT_NOT_FOUND);
		Assert.notNull(orderDTO.getReference(), ErrorMessages.OBJECT_NOT_FOUND);
		Assert.notNull(orderDTO.getIsPaid(), ErrorMessages.OBJECT_NOT_FOUND);
		Assert.notNull(orderDTO.getIsShipped(), ErrorMessages.OBJECT_NOT_FOUND);
		Assert.notNull(orderDTO.getCustomer(), ErrorMessages.OBJECT_NOT_FOUND);
		Assert.notEmpty(orderDTO.getProducts(), ErrorMessages.LIST_IS_EMPTY);

		int alreadyExist = orderRepository.countByReferenceIgnoreCase(orderDTO.getReference());
		if (alreadyExist > 0) {
			throw new ObjectAlreadyExists(Order.class.getSimpleName());
		}
		var order = new Order();

		// set order basic info
		order.setReference(orderDTO.getReference());
		order.setIsPaid(orderDTO.getIsPaid());
		order.setIsShipped(orderDTO.getIsShipped());
		order.setIsCancelled(false);

		// set order customer
		order.setCustomer(userService.mapSimpleDTOToEntity(orderDTO.getCustomer()));

		save(order);
		// set order products
		var totalAmount = 0.0;
		Set<Product> products = new HashSet<>();
		for (SimpleProductDTO product : orderDTO.getProducts()) {
			Assert.notNull(product.getPrice(), "Product price cannot be null");
			Assert.notNull(product.getColor(), "Product color cannot be null");
			Assert.notNull(product.getName(), ErrorMessages.MISSING_NAME);
			Assert.notNull(product.getPrice(), ErrorMessages.MISSING_SELLING_PRICE);
			Assert.notNull(product.getSize(), "Product size cannot be null");
			Assert.notNull(product.getStockId(), "Product :  Stock ID cannot be null");
			products.add(
					productService.mapSimpleDTOToEntity(productService.createFromOrder(product, order, order.getCustomer())));
			totalAmount += product.getPrice();
		}

		order.setProducts(orderDTO.getProducts().parallelStream().map(p -> productService.mapSimpleDTOToEntity(p))
				.collect(Collectors.toSet()));
		order.setTotalAmount(totalAmount);

		return mapEntityToDTO(save(order));
	}

	@Override
	public OrderDTO edit(OrderDTO orderDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO softDelete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDTO undelete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public OrderDTO mapEntityToDTO(Order order) {
		return mapper.map(order, OrderDTO.class);
	}

	@Override
	public Order mapDTOToEntity(OrderDTO dto) {
		return mapper.map(dto, Order.class);
	}

	@Override
	public SimpleOrderDTO mapEntityToSimpleDTO(Order order) {
		return mapper.map(order, SimpleOrderDTO.class);
	}

	@Override
	public Order mapSimpleDTOToEntity(SimpleOrderDTO dto) {
		return mapper.map(dto, Order.class);
	}

}
