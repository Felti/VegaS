package com.vega.app.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.vega.app.entities.ext.Auditable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "authuser")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class AuthUser extends Auditable {

	private static final long serialVersionUID = 1796122512306351925L;

	@Column(name = "externalId")
	private Long externalId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "login", unique = true)
	private String login;

	@Column(name = "password")
	private String password;

}
