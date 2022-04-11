package com.vega.app.dtos;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentDTO extends BaseDTO {

	private Long id;

	private String content;

	private PostDTO post;
	
	private SimpleUserDTO user;

	public CommentDTO(Long id, String content) {
		super(id);
		this.content = content;

	}

	public CommentDTO(Long id, LocalDateTime createdAt, String content) {
		super(id, createdAt);
		this.content = content;

	}
	
	public CommentDTO(Long id, LocalDateTime createdAt, String content, Long userId, String userFirstName, String userLastName, String userPictureUrl) {
		super(id, createdAt);
		this.content = content;
		this.user = new SimpleUserDTO(userId, userFirstName, userLastName, userPictureUrl);

	}
	

}
