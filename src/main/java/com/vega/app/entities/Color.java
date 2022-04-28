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
@Table(name = "vega_color")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Color extends Auditable {

	private static final long serialVersionUID = 5311804321437370246L;

	private String name;

	private Integer available;

	@ManyToOne
	@JoinColumn(name = "feature_id")
	private Feature feature;

}
