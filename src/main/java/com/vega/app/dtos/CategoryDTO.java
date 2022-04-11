package com.vega.app.dtos;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryDTO extends BaseDTO {

	private String name;

	private Set<PostDTO> posts;

	public CategoryDTO(Long id, String name) {
		super(id);
		this.name = name;
	}

	public CategoryDTO(Long id, Boolean deleted, LocalDateTime createdAt, LocalDateTime modifiedAt, String name,
			Set<PostDTO> posts) {
		super(id, deleted, createdAt, modifiedAt);
		this.name = name;
		this.posts = posts;
	}

}
