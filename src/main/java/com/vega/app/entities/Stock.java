package com.vega.app.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vega.app.entities.ext.Auditable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "vega_stock")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Stock extends Auditable {

	private static final long serialVersionUID = 2425256262317399568L;

	private String name;

	private String description;

	private Integer quantity;

	private Double unitPrice;

	private Double sellingPrice;

	private Double total;

	// Many stocks can belong to one providers
	@ManyToOne
	@JoinColumn(name = "provider_id")
	private User provider;

	// One stock can have many invoices // we can buy multiple stocks and create
	@OneToMany(mappedBy = "stock")
	private Set<Invoice> invoices;

	// One stock can have many products
	@OneToMany(mappedBy = "stock")
	private Set<Product> produits;
	
	// One stock can have many products
	@OneToMany(mappedBy = "stock")
	private Set<Size> sizes;

	// Many stocks can have many tags
	@ManyToMany
	@JoinTable(name = "mtm_stock_tag", joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "stock_id", referencedColumnName = "id"))
	private Set<Tag> tags = new HashSet<>();

}
