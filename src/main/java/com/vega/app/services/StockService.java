package com.vega.app.services;

import org.springframework.data.domain.Page;

import com.vega.app.dtos.PageableDTO;
import com.vega.app.dtos.StockDTO;
import com.vega.app.entities.Stock;

public interface StockService {

	Page<StockDTO> getAll(PageableDTO page, Boolean deleted);

	StockDTO saveToDTO(Stock stock);

	Stock save(Stock stock);

	Stock getById(Long id);
	
	Boolean addQuantity(Long id , Integer quantity);

	StockDTO getDTOById(Long id);

	StockDTO create(StockDTO stockDTO);

	StockDTO edit(StockDTO stockDTO);

	StockDTO softDelete(Long id);

	StockDTO undelete(Long id);

	StockDTO mapEntityToDTO(Stock stock);

	Stock mapDTOToEntity(StockDTO dto);


}
