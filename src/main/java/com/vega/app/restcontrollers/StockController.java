package com.vega.app.restcontrollers;

import java.util.Date;

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
import com.vega.app.entities.front.CostStats;
import com.vega.app.entities.front.RevenuStats;
import com.vega.app.entities.request.PageRequest;
import com.vega.app.services.StockService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("api/stocks")
public class StockController {

	@Autowired
	StockService stockService;

	@Operation(summary = "Used to get paged list of Stocks", description = "Takes as RequestBody a PageDTO object")
	@PostMapping("/all")
	public ResponseEntity<CustomResponse<Page<StockDTO>>> getAll(@RequestBody PageRequest request) {
		return new ResponseEntity<>(
				new CustomResponse<>(stockService.getAll(request.getPageRequest(), request.getDeleted())), HttpStatus.OK);
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

	@GetMapping("stats/revenu")
	public ResponseEntity<CustomResponse<RevenuStats>> stocksRevenuStats(@RequestParam(name = "start") Date start,
			@RequestParam(name = "end") Date end) {
		return new ResponseEntity<>(new CustomResponse<>(stockService.getStocksRevenuStats(start, end)), HttpStatus.OK);
	}

	@GetMapping("stats/costs")
	public ResponseEntity<CustomResponse<CostStats>> stockCostsStats(@RequestParam(name = "start") Date start,
			@RequestParam(name = "end") Date end) {
		return new ResponseEntity<>(new CustomResponse<>(stockService.getStocksCostsStats(start, end)), HttpStatus.OK);
	}

}
