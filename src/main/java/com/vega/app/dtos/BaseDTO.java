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
public class BaseDTO {
	
	private Long id;

	private Boolean deleted;

	private LocalDateTime createdAt;

	private LocalDateTime modifiedAt;

	public BaseDTO(Long id) {
		super();
		this.id = id;
	}

	public BaseDTO(Long id, Boolean deleted) {
		super();
		this.id = id;
		this.deleted = deleted;

	}

	public BaseDTO(Long id, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.createdAt = createdAt;
	}

	public BaseDTO(Long id, Boolean deleted, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.deleted = deleted;
		this.createdAt = createdAt;
	}

	public BaseDTO(Long id, LocalDateTime createdAt, LocalDateTime modifiedAt) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public BaseDTO(Long id, Boolean deleted, LocalDateTime createdAt, LocalDateTime modifiedAt) {
		super();
		this.id = id;
		this.deleted = deleted;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}


}
