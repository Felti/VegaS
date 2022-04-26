package com.vega.app.entities;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vega.app.entities.ext.Auditable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "vega_invoice")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Invoice extends Auditable {

	private static final long serialVersionUID = 2058867750046970727L;

	private Double amount;

	private String reference;

	private Boolean isPaid;

	private Boolean isCancelled;
	
	private Boolean forProvider;
	
	private Boolean forClient;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne
	@JoinTable(name = "oto_invoice_order")
	private Order order;

	@ManyToOne
	@JoinTable(name = "stock_id")
	private Stock stock;

	@OneToMany(mappedBy = "order")
	private Set<Product> products;

}
