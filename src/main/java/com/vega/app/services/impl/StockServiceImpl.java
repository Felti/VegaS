package com.vega.app.services.impl;

import java.util.Date;
import java.util.HashSet;
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
import com.vega.app.dtos.StockDTO;
import com.vega.app.dtos.simple.SimpleFeatureDTO;
import com.vega.app.dtos.simple.SimpleStockDTO;
import com.vega.app.dtos.simple.SimpleTagDTO;
import com.vega.app.entities.Feature;
import com.vega.app.entities.Stock;
import com.vega.app.entities.front.CostStats;
import com.vega.app.entities.front.RevenuStats;
import com.vega.app.repositories.StockRepository;
import com.vega.app.services.TagService;
import com.vega.app.services.UserService;
import com.vega.app.services.CategoryService;
import com.vega.app.services.ColorService;
import com.vega.app.services.FeatureService;
import com.vega.app.services.StockService;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepository;

	@Autowired
	TagService tagService;

	@Autowired
	UserService userService;

	@Autowired
	FeatureService featureService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ColorService colorService;

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
			stocks.getContent().parallelStream().forEach(s -> {
				s.setFeatures(appendFeaturesToStock(s.getId()));

			});

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
	public SimpleStockDTO getSimpleDTOById(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		return stockRepository.findSimpleDTOById(id);
	}

	@Override
	public StockDTO create(StockDTO stockDTO) {

		Assert.notNull(stockDTO, ErrorMessages.OBJECT_NOT_FOUND);
		Assert.notNull(stockDTO.getProvider(), ErrorMessages.OBJECT_NOT_FOUND);
		Assert.notNull(stockDTO.getUnitPrice(), ErrorMessages.MISSING_UNIT_PRICE);
		Assert.notNull(stockDTO.getSellingPrice(), ErrorMessages.MISSING_SELLING_PRICE);

		var stock = new Stock();

		// Set basic info
		if (!StringUtils.isBlank(stockDTO.getName())) {
			stock.setName(stockDTO.getName().trim());
		}

		if (!StringUtils.isBlank(stockDTO.getDescription())) {
			stock.setDescription(stockDTO.getDescription().trim());
		}

		stock.setUnitPrice(stockDTO.getUnitPrice());
		stock.setSellingPrice(stockDTO.getSellingPrice());

		stock.setProvider(userService.mapSimpleDTOToEntity(stockDTO.getProvider()));

		// Set tags
		if (!stockDTO.getTags().isEmpty()) {
			stock.setTags(
					stockDTO.getTags().parallelStream().map(t -> tagService.mapSimpleDTOToEntity(t)).collect(Collectors.toSet()));
		}

		save(stock);

		// set features + set total elements
		if (!CollectionUtils.isEmpty(stockDTO.getFeatures())) {

			// stock left
			stock.setQuantity(featureService.addFeaturesToStock(stockDTO.getFeatures(), stock));

			// stock of start
			stock.setInitialQuantity(stock.getQuantity() != null ? stock.getQuantity() : 0);

			stock.setFeatures(featureService.getFeaturesOfStock(stock.getId()).parallelStream()
					.map(f -> featureService.mapSimpleDTOToEntity(f)).collect(Collectors.toSet()));
		}

		stock.setTotal(stockDTO.getUnitPrice() * (stock.getQuantity() != null ? stock.getQuantity() : 1));

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

	public Set<SimpleTagDTO> appendTagsToStock(Long stockId) {
		Assert.notNull(stockId, ErrorMessages.ID_NOT_FOUND);
		return tagService.getTagsOfStock(stockId);
	}

	public Set<SimpleFeatureDTO> appendFeaturesToStock(Long stockId) {
		Assert.notNull(stockId, ErrorMessages.ID_NOT_FOUND);
		Set<SimpleFeatureDTO> features = featureService.getFeaturesOfStock(stockId);
		if (!CollectionUtils.isEmpty(features)) {
			features.parallelStream().forEach(f -> f.setColors(colorService.getColorsOfFeature(f.getId())));
			return features;
		}
		return new HashSet<>();
	}

	@Override
	public Stock save(Stock stock) {
		return stockRepository.save(stock);
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
	public Stock mapSimpleDTOToEntity(SimpleStockDTO dto) {
		return mapper.map(dto, Stock.class);
	}

	@Override
	public StockDTO saveToDTO(Stock stock) {
		return mapEntityToDTO(stockRepository.save(stock));
	}

	@Override
	public void updateStockQuantity(Long id) {
		Assert.notNull(id, ErrorMessages.ID_NOT_FOUND);
		var stock = getById(id);
		var totalAvailable = 0;
		for (Feature f : stock.getFeatures()) {
			totalAvailable += f.getNbrAvailable();
		}
		stock.setQuantity(totalAvailable);
		save(stock);
	}

	@Override
	public RevenuStats getStocksRevenuStats(Date start, Date end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CostStats getStocksCostsStats(Date start, Date end) {
		// TODO Auto-generated method stub
		return null;
	}

}
