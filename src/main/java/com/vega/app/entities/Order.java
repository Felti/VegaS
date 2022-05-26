package com.vega.app.entities;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vega.app.entities.ext.Auditable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vega_order")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Order extends Auditable {

	private static final long serialVersionUID = 7872981209180175489L;

	private String reference;

	private Boolean isCancelled;

	private Boolean isPaid;

	private Boolean isShipped;

	private Double totalAmount;

	@OneToOne
	private Invoice invoice;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User customer;

	@OneToMany(mappedBy = "order")
	private Set<Product> products;

}
