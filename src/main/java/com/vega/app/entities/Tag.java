package com.vega.app.entities;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "vega_tag")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Tag extends Auditable {

	private static final long serialVersionUID = -5950674390304513159L;

	private String name;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	// many stocks can have many tags
	@ManyToMany(mappedBy = "tags")
	private Set<Stock> stocks;

}
