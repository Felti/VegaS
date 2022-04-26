package com.vega.app.entities;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vega.app.entities.ext.Auditable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vega_order_status")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class OrderStatus extends Auditable {

	private static final long serialVersionUID = 6813321775057173788L;

	private String name;

	@OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
	private Set<Order> orders;

}
