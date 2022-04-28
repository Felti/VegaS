package com.vega.app.entities;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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
@Table(name = "vega_feature")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Feature extends Auditable {

	private static final long serialVersionUID = 548553898923340511L;
	
	private String name;

	private String size;

	private String hight;

	private Integer sizeNumber;

	private Integer nbrAvailable;

	@OneToMany(mappedBy = "feature",fetch = FetchType.EAGER)
	private Set<Color> colors;

	// Many features can belong to one stock
	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stock stock;

}
