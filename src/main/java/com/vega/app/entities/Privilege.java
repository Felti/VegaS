package com.vega.app.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.vega.app.entities.ext.Auditable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vega_privilege")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Privilege extends Auditable {

	private static final long serialVersionUID = -5003754265944332099L;

	private String name;

	@ManyToMany(mappedBy = "privileges")
	private Set<Role> roles;

	@ManyToMany(mappedBy = "privileges")
	private Set<User> users;

	public Privilege() {
		roles = new HashSet<>();
		users = new HashSet<>();
	}
	
	public void removeRole(Role role) {
		roles.remove(role);
		role.getPrivileges().remove(this);
	}

	public void removeRoles() {
		getRoles().forEach(role -> role.getPrivileges().remove(this));
		setRoles(new HashSet<>());
	}

	public void removeUser(User user) {
		users.remove(user);
		user.getPrivileges().remove(this);
	}

	public void removeUsers() {
		getUsers().forEach(user -> user.getPrivileges().remove(this));
		setUsers(new HashSet<>());
	}

}
