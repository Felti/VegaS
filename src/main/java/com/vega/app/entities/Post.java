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
@Table(name = "vega_post")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class Post extends Auditable {

	private static final long serialVersionUID = 8821929208606239169L;

	private String title;

	private String content;
	
	private Long numberSeen;
	
	private Long nbrClicks;
	
	private Long nbrComments;
	
	private Long nbrReactions;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
	private Set<Comment> comments;

	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
	private Set<Reaction> reactions;

}
