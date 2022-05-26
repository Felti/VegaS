package com.vega.app.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vega.app.dtos.OrderDTO;
import com.vega.app.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<Order> findById(Long id);

	@Query("SELECT new com.vega.app.dtos.OrderDTO(o.id, o.deleted, o.createdAt, o.modifiedAt, o.reference, o.isCancelled, o.isPaid, o.isShipped, "
			+ " o.totalAmount, o.customer.id, o.customer.firstName, o.customer.lastName, o.customer.phoneNumber, o.customer.email) "
			+ " FROM Order o WHERE o.deleted = ?1 ORDER BY o.createdAt DESC")
	Page<OrderDTO> findAllByDeleted(Boolean deleted, Pageable page);

	int countByReferenceIgnoreCase(String reference);

}
