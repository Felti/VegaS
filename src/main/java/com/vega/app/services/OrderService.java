package com.vega.app.services;

import org.springframework.data.domain.Page;

import com.vega.app.dtos.OrderDTO;
import com.vega.app.dtos.PageableDTO;
import com.vega.app.dtos.simple.SimpleOrderDTO;
import com.vega.app.entities.Order;

public interface OrderService {

	Order getById(Long id);

	Page<OrderDTO> getAll(PageableDTO pageDTO, Boolean deleted);

	OrderDTO getDTOById(Long id);

	OrderDTO create(OrderDTO orderDTO);

	OrderDTO edit(OrderDTO orderDTO);

	OrderDTO softDelete(Long id);

	OrderDTO undelete(Long id);

	void delete(Long id);

	OrderDTO mapEntityToDTO(Order product);

	Order mapDTOToEntity(OrderDTO dto);

	Order save(Order order);

	SimpleOrderDTO mapEntityToSimpleDTO(Order order);

	Order mapSimpleDTOToEntity(SimpleOrderDTO dto);

}
