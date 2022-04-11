package com.vega.app.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vega.app.entities.ext.Auditable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vega_user")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class User extends Auditable {

	private static final long serialVersionUID = -1009964581947385203L;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "legend")
	private String legend;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "pictureUrl")
	private String pictureUrl;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "login", unique = true)
	private String login;

	@Column(name = "password")
	private String password;

	@Column(name = "is_enabled")
	private Boolean isEnabled;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Post> posts;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Comment> comments;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Set<Reaction> reactions;

	@ManyToMany
	@JoinTable(name = "mtm_users_friends", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
	private Set<User> friends = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "mtm_users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;

	@ManyToMany
	@JoinTable(name = "mtm_users_privileges", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
	private Set<Privilege> privileges;

	public void befriendUser(@NonNull User user) {
		friends.add(user);
		user.getFriends().add(this);
	}

	public void removeFriend(@NonNull User user) {
		friends.remove(user);
	}

}
