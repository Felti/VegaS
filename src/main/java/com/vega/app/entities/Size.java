package com.vega.app.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vega.app.entities.ext.Auditable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "vega_size")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Size extends Auditable {

	private static final long serialVersionUID = 548553898923340511L;

	private String name;

	private String displayName;

	private Integer nbrAvailable;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	// Many sizes can belong to one stock
	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stock stock;

}
