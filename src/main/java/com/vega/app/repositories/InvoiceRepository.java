package com.vega.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vega.app.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{

}
