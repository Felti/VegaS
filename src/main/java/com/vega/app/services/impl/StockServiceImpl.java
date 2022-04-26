package com.vega.app.services.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.vega.app.constants.ErrorMessages;
import com.vega.app.dtos.PageableDTO;
import com.vega.app.dtos.SizeDTO;
import com.vega.app.dtos.StockDTO;
import com.vega.app.dtos.simple.SimpleTagDTO;
import com.vega.app.entities.Stock;
import com.vega.app.repositories.StockRepository;
import com.vega.app.services.StockService;
import com.vega.app.services.TagService;
import com.vega.app.services.UserService;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;

	@Autowired
	TagService tagService;

	@Autowired
	UserService userService;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Page<StockDTO> getAll(PageableDTO page, Boolean deleted) {
		Assert.notNull(page.getPage(), ErrorMessages.PAGE_NUMBER_MISSING);
		Assert.notNull(page.getSize(), ErrorMessages.PAGE_SIZE_MISSING);
		Assert.notNull(deleted, "deleted " + ErrorMessages.OBJECT_NOT_FOUND);

		// Create page request
		Pageable pageRequest = PageRequest.of(page.getPage() - 1, page.getSize());

		Page<StockDTO> stocks = stockRepository.findAllByDeleted(deleted, pageRequest);
		if (!CollectionUtils.isEmpty(stocks.getContent())) {
			stocks.getContent().parallelStream().forEach(s -> s.setTags(appendTagsToStock(s.getId())));

		}

		return stocks;
	}

	@Override
	public Stock getById(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);

		Optional<Stock> stock = stockRepository.findById(id);
		if (stock.isPresent()) return stock.get();

		return null;
	}

	@Override
	public StockDTO getDTOById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addQuantity(Long id, Integer quantity) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);

		var existingStock = getById(id);

		Assert.notNull(existingStock, ErrorMessages.OBJECT_NOT_FOUND);

		existingStock.setQuantity(existingStock.getQuantity() + quantity);
		existingStock.setTotal(updateTotal(existingStock.getUnitPrice(), existingStock.getQuantity()));

		save(existingStock);

		// Create a new invoice after that

		return true;
	}

	@Override
	public StockDTO create(StockDTO stockDTO) {
		Assert.notNull(stockDTO, ErrorMessages.OBJECT_NOT_FOUND);
		Assert.notNull(stockDTO.getProvider(), ErrorMessages.OBJECT_NOT_FOUND);
		Assert.notNull(stockDTO.getQuantity(), ErrorMessages.MISSING_QUANTITY);
		Assert.notNull(stockDTO.getUnitPrice(), ErrorMessages.MISSING_UNIT_PRICE);
		Assert.notNull(stockDTO.getSellingPrice(), ErrorMessages.MISSING_SELLING_PRICE);
		Assert.notNull(stockDTO.getSellingPrice(), ErrorMessages.MISSING_SELLING_PRICE);

		var stock = new Stock();
		
		// set total elements
		int totalElements = 0;
		for (SizeDTO s : stockDTO.getSizes()) {
			if (s.getNbrAvailable() != null) {
				totalElements += s.getNbrAvailable();
			}
		
		}

		// Set basic info
		if (!StringUtils.isBlank(stockDTO.getName())) {
			stock.setName(stockDTO.getName().trim());
		}

		stock.setDescription(stockDTO.getDescription().trim());
		stock.setQuantity(totalElements);
		stock.setUnitPrice(stockDTO.getUnitPrice());
		stock.setSellingPrice(stockDTO.getSellingPrice());
		stock.setTotal(stockDTO.getUnitPrice() * stockDTO.getQuantity());
		stock.setProvider(userService.mapSimpleDTOToEntity(stockDTO.getProvider()));

		if (!stockDTO.getTags().isEmpty()) {
			stock.setTags(
					stockDTO.getTags().parallelStream().map(t -> tagService.mapSimpleDTOToEntity(t)).collect(Collectors.toSet()));
		}

		return saveToDTO(stock);
	}

	@Override
	public StockDTO edit(StockDTO stockDTO) {

		Assert.notNull(stockDTO.getId(), ErrorMessages.ID_NOT_FOUND);

		var existingStock = getById(stockDTO.getId());

		Assert.notNull(existingStock, ErrorMessages.OBJECT_NOT_FOUND);

		if (!StringUtils.isBlank(stockDTO.getName())) {
			existingStock.setName(stockDTO.getName());
		}

		if (!StringUtils.isBlank(stockDTO.getDescription())) {
			existingStock.setDescription(stockDTO.getDescription());
		}

		if (stockDTO.getQuantity() != null) {
			existingStock.setQuantity(stockDTO.getQuantity());
		}

		if (stockDTO.getSellingPrice() != null) {
			existingStock.setUnitPrice(stockDTO.getSellingPrice());
		}

		if (stockDTO.getSellingPrice() != null && stockDTO.getQuantity() != null) {
			existingStock.setTotal(updateTotal(stockDTO.getUnitPrice(), stockDTO.getQuantity()));
		}

		if (ObjectUtils.isNotEmpty(stockDTO.getProvider())) {
			existingStock.setProvider(userService.mapSimpleDTOToEntity(stockDTO.getProvider()));
		} else throw new NullPointerException("Please chose a provider.");

		if (!CollectionUtils.isEmpty(stockDTO.getTags())) {
			existingStock.setTags(
					stockDTO.getTags().parallelStream().map(t -> tagService.mapSimpleDTOToEntity(t)).collect(Collectors.toSet()));
		} else existingStock.setTags(new HashSet<>());

		return mapEntityToDTO(save(existingStock));
	}

	@Override
	public StockDTO softDelete(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		var stock = getById(id);
		Assert.notNull(id, ErrorMessages.OBJECT_NOT_FOUND);
		stock.setDeleted(true);
		return mapEntityToDTO(save(stock));
	}

	@Override
	public StockDTO undelete(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		var stock = getById(id);
		Assert.notNull(id, ErrorMessages.OBJECT_NOT_FOUND);
		stock.setDeleted(false);
		return mapEntityToDTO(save(stock));
	}

	public Double updateTotal(Double unitPrice, Integer quantity) {
		Assert.notNull(unitPrice, "unitPrice is null");
		Assert.notNull(quantity, "quantity is null");

		return unitPrice * quantity;
	}

	@Override
	public Stock save(Stock stock) {
		return stockRepository.save(stock);
	}

	public Set<SimpleTagDTO> appendTagsToStock(Long stockId) {
		Assert.notNull(stockId, ErrorMessages.ID_NOT_FOUND);
		return tagService.getTagsOfStock(stockId);
	}

	@Override
	public StockDTO mapEntityToDTO(Stock stock) {
		return mapper.map(stock, StockDTO.class);
	}

	@Override
	public Stock mapDTOToEntity(StockDTO dto) {
		return mapper.map(dto, Stock.class);
	}

	@Override
	public StockDTO saveToDTO(Stock stock) {
		return mapEntityToDTO(stockRepository.save(stock));
	}

}
