package com.vega.app.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vega.app.dtos.simple.SimpleProductDTO;
import com.vega.app.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findById(Long id);

	@Query("SELECT new com.vega.app.dtos.simple.SimpleProductDTO(p.id, p.name, p.color, p.size, p.price) FROM Product p WHERE p.order.id = ?1 AND deleted is false")
	Set<SimpleProductDTO> findProductsOfOrder(Long id);
}
