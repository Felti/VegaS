package com.vega.app.dtos;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostDTO extends BaseDTO {

	private String title;

	private String content;

	private SimpleCategoryDTO category;

	private SimpleUserDTO user;

	private Long numberSeen;

	private Long nbrClicks;

	private Long nbrComments;

	private Long nbrReactions;

	private Set<CommentDTO> comments;

	private Set<ReactionDTO> reactions;

	public PostDTO(Long id) {
		super(id);
	}

	public PostDTO(Long id, Boolean deleted, LocalDateTime createdAt, String title, Long categoryId,
			String categoryName) {
		super(id, deleted, createdAt);
		this.title = title;
		this.category = new SimpleCategoryDTO(categoryId, categoryName);
	}
	public PostDTO(Long id, Boolean deleted, String title,String content, Long categoryId,
			String categoryName,Long userId,String userFirstName, String userLastName) {
		super(id, deleted);
		this.title = title;
		this.content = content;
		this.category = new SimpleCategoryDTO(categoryId, categoryName);
		this.user = new SimpleUserDTO(userId, userFirstName, userLastName);
	}

	public PostDTO(Long id, Boolean deleted, LocalDateTime createdAt, LocalDateTime modifiedAt, String title,
			String content, Long categoryId, String categoryName) {
		super(id, deleted, createdAt, modifiedAt);
		this.title = title;
		this.content = content;
		this.category = new SimpleCategoryDTO(categoryId, categoryName);
		this.comments = new HashSet<>();
		this.reactions = new HashSet<>();
	}

	public PostDTO(Long id, Boolean deleted, LocalDateTime createdAt, LocalDateTime modifiedAt, String title,
			String content, Long categoryId, String categoryName, Long numberSeen, Long nbrClicks, Long nbrComments,
			Long nbrReactions) {
		super(id, deleted, createdAt, modifiedAt);
		this.title = title;
		this.content = content;
		this.category = new SimpleCategoryDTO(categoryId, categoryName);
		this.numberSeen = numberSeen;
		this.nbrClicks = nbrClicks;
		this.nbrComments = nbrComments;
		this.nbrReactions = nbrReactions;
	}

	public PostDTO(Long id, Boolean deleted, LocalDateTime createdAt, LocalDateTime modifiedAt, String title,
			String content, Long categoryId, String categoryName, Long userId, String userFirstName, String userlastName,
			Long numberSeen, Long nbrClicks, Long nbrComments, Long nbrReactions) {
		super(id, deleted, createdAt, modifiedAt);
		this.title = title;
		this.content = content;
		this.category = new SimpleCategoryDTO(categoryId, categoryName);
		this.user = new SimpleUserDTO(userId, userFirstName, userlastName);
		this.numberSeen = numberSeen;
		this.nbrClicks = nbrClicks;
		this.nbrComments = nbrComments;
		this.nbrReactions = nbrReactions;
	}

}
