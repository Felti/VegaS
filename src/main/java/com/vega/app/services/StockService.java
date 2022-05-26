package com.vega.app.services;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.vega.app.dtos.PageableDTO;
import com.vega.app.dtos.StockDTO;
import com.vega.app.dtos.simple.SimpleStockDTO;
import com.vega.app.entities.Stock;
import com.vega.app.entities.front.CostStats;
import com.vega.app.entities.front.RevenuStats;

public interface StockService {

	Page<StockDTO> getAll(PageableDTO page, Boolean deleted);

	Stock getById(Long id);

	StockDTO getDTOById(Long id);

	SimpleStockDTO getSimpleDTOById(Long id);

	StockDTO create(StockDTO stockDTO);

	StockDTO edit(StockDTO stockDTO);

	StockDTO softDelete(Long id);

	StockDTO undelete(Long id);

	void updateStockQuantity(Long id);

	RevenuStats getStocksRevenuStats(Date start, Date end);

	CostStats getStocksCostsStats(Date start, Date end);

	Stock save(Stock stock);

	StockDTO saveToDTO(Stock stock);

	StockDTO mapEntityToDTO(Stock stock);

	Stock mapDTOToEntity(StockDTO dto);

	Stock mapSimpleDTOToEntity(SimpleStockDTO dto);

}
