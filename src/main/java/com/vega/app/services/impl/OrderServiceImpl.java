package com.vega.app.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.vega.app.dtos.OrderDTO;
import com.vega.app.dtos.PageableDTO;
import com.vega.app.entities.Order;
import com.vega.app.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public Order getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<OrderDTO> getAll(PageableDTO pageDTO) {
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
		// TODO Auto-generated method stub
		return null;
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
	public OrderDTO mapEntityToDTO(Order product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order mapDTOToEntity(OrderDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
