package com.vega.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vega.app.dtos.StockDTO;
import com.vega.app.dtos.simple.SimpleStockDTO;
import com.vega.app.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

	@Query("SELECT new com.vega.app.dtos.StockDTO( s.id, s.deleted, s.createdAt, s.modifiedAt, s.name, s.description, s.initialQuantity, s.quantity, s.unitPrice, s.sellingPrice, s.total, s.provider.id, s.provider.firstName, s.provider.lastName ) FROM Stock s WHERE s.deleted = ?1 ORDER BY s.createdAt DESC")
	Page<StockDTO> findAllByDeleted(Boolean deleted, Pageable pageRequest);

	@Query("SELECT new com.vega.app.dtos.simple.SimpleStockDTO(s.id, s.name) FROM Stock s where id = ?1")
	SimpleStockDTO findSimpleDTOById(Long id);

}
