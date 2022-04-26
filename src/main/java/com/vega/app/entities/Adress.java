package com.vega.app.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "vega_Adress")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Adress extends Auditable {

	private static final long serialVersionUID = 2222982034737272618L;

	private String name;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
