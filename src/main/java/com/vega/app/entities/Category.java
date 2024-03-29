package com.vega.app.entities;

import java.util.HashSet;
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

@Table(name = "vega_category")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Category extends Auditable {

	private static final long serialVersionUID = 6813321775057173788L;

	private String name;

	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Set<Post> posts;

	public Category() {
		super();
		posts = new HashSet<>();
	}

}
