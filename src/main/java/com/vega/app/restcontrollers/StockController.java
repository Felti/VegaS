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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vega.app.dtos.StockDTO;
import com.vega.app.entities.CustomResponse;
import com.vega.app.entities.request.StockRequest;
import com.vega.app.services.StockService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/stocks")
public class StockController {

	@Autowired
	StockService stockService;

	@Operation(summary = "Used to get paged list of Stocks", description = "Takes as RequestBody a PageDTO object")
	@PostMapping("/all")
	public ResponseEntity<CustomResponse<Page<StockDTO>>> getAll(@RequestBody StockRequest request) {
		return new ResponseEntity<>(
				new CustomResponse<>(stockService.getAll(request.getPageRequest(), request.getDeleted())), HttpStatus.OK);
	}

	@Operation(summary = "Used to Add more units into a stock", description = "Adds more unit and create a new invoice for the same provider/stock")
	@GetMapping("/add")
	public ResponseEntity<CustomResponse<Boolean>> add(@RequestParam(name = "id") Long id, @RequestParam(name = "quantity") Integer quantity) {
		return new ResponseEntity<>(
				new CustomResponse<>(stockService.addQuantity(id, quantity), "Added " + quantity + " Unit succesfully"),
				HttpStatus.OK);
	}

	@Operation(summary = "Used to create an order", description = "")
	@PostMapping
	public ResponseEntity<CustomResponse<StockDTO>> create(@RequestBody StockDTO stock) {
		return new ResponseEntity<>(new CustomResponse<>(stockService.create(stock), "Your stock has been created"),
				HttpStatus.OK);
	}

	@Operation(summary = "Used to edit an order", description = "")
	@PatchMapping
	public ResponseEntity<CustomResponse<StockDTO>> edit(@RequestBody StockDTO stockDTO) {
		return new ResponseEntity<>(new CustomResponse<>(stockService.edit(stockDTO), "Your stock has been edited"),
				HttpStatus.OK);
	}

	@Operation(summary = "Used to soft deleted an order", description = "Set deleted = true")
	@GetMapping("delete/{id}")
	public ResponseEntity<CustomResponse<StockDTO>> solfDeleted(@PathVariable Long id) {
		return new ResponseEntity<>(new CustomResponse<>(stockService.softDelete(id), "Your stock has been deleted"),
				HttpStatus.OK);
	}

	@Operation(summary = "Used to soft undeleted an order", description = "Set deleted = false")
	@GetMapping("undelete/{id}")
	public ResponseEntity<CustomResponse<StockDTO>> undelete(@PathVariable Long id) {
		return new ResponseEntity<>(new CustomResponse<>(stockService.undelete(id), "Your stock has been restored"),
				HttpStatus.OK);
	}

}
