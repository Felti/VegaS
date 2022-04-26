package com.vega.app.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vega.app.dtos.OrderDTO;
import com.vega.app.dtos.PageableDTO;
import com.vega.app.entities.CustomResponse;
import com.vega.app.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Operation(summary = "Used to get all orders", description = "Takes as RequestBody a PageDTO object")
	@PostMapping("/all")
	public ResponseEntity<CustomResponse<Page<OrderDTO>>> all(@RequestBody PageableDTO pageDTO) {
		return new ResponseEntity<>(new CustomResponse<>(orderService.getAll(pageDTO), "got all the orders :')"),
				HttpStatus.OK);
	}

	@Operation(summary = "Used to create a new order", description = "")
	@PostMapping
	public ResponseEntity<CustomResponse<OrderDTO>> create(@RequestBody OrderDTO order) {
		return new ResponseEntity<>(new CustomResponse<>(orderService.create(order), "Your order has been created"),
				HttpStatus.CREATED);
	}

	@Operation(summary = "Used to get one instance of a order", description = "")
	@GetMapping("{id}")
	public ResponseEntity<CustomResponse<OrderDTO>> getById(@PathVariable Long id) {
		return new ResponseEntity<>(new CustomResponse<>(orderService.getDTOById(id)), HttpStatus.CREATED);
	}

	@Operation(summary = "Used to get edit instance of a order", description = "")
	@PatchMapping
	public ResponseEntity<CustomResponse<OrderDTO>> edit(@RequestBody OrderDTO order) {
		return new ResponseEntity<>(new CustomResponse<>(orderService.edit(order), "Your order has been edited"),
				HttpStatus.OK);
	}

	@Operation(summary = "Used to get softe deleted an instance of a order", description = " Set an order to deleted = true")
	@GetMapping("delete/{id}")
	public ResponseEntity<CustomResponse<OrderDTO>> softDelete(@PathVariable Long id) {
		return new ResponseEntity<>(new CustomResponse<>(orderService.softDelete(id), "Your order has been created"),
				HttpStatus.OK);
	}

}
