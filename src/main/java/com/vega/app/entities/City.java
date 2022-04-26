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
@Table(name = "vega_city")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class City extends Auditable{
	
	private static final long serialVersionUID = -5600496184897188154L;

	private String name;

	@OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
	private Set<User> users;

}
