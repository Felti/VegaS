package com.vega.app.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.vega.app.entities.ext.Auditable;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vega_role")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Role extends Auditable {

	private static final long serialVersionUID = 7548186291967472495L;

	private String name;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	@ManyToMany
	@JoinTable(name = "mtm_roles_privileges", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
	private Set<Privilege> privileges;

	public Role() {
		users = new HashSet<>();
		privileges = new HashSet<>();
	}
	
	public void addPrivilege(@NonNull Privilege privilege) {
		privileges.add(privilege);
		privilege.getRoles().add(this);
	}

	public void addPrivileges(@NonNull Set<Privilege> privileges) {
		for (Privilege privilege : privileges) {
			addPrivilege(privilege);
		}
	}

	public void removePrivilege(Privilege privilege) {
		privileges.remove(privilege);
		privilege.getRoles().remove(this);
	}

	public void removePrivileges() {
		getPrivileges().forEach(privilege -> privilege.getRoles().remove(this));
		setPrivileges(new HashSet<>());
	}

	public void removeUser(User user) {
		users.remove(user);
		user.getRoles().remove(this);
	}

	public void removeUsers() {
		getUsers().forEach(user -> user.getRoles().remove(this));
		setUsers(new HashSet<>());
	}


}
