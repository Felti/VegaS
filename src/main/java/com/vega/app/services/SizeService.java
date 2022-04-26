package com.vega.app.services;

import java.util.Set;

import com.vega.app.dtos.SizeDTO;
import com.vega.app.dtos.StockDTO;
import com.vega.app.entities.Category;
import com.vega.app.entities.Size;

public interface SizeService {

	Set<SizeDTO> addSizesToStock(Set<SizeDTO> sizes, StockDTO stock);

	Set<SizeDTO> removeSizesFromStock(Set<SizeDTO> sizes, StockDTO stock);

	SizeDTO mapEntityToDTO(Category size);

	Size mapDTOToEntity(SizeDTO sizeDTO);

}
