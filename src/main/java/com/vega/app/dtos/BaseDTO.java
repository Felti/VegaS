package com.vega.app.dtos;

import java.util.Date;

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

	private Date createdAt;

	private Date modifiedAt;

	public BaseDTO(Long id) {
		super();
		this.id = id;
	}

	public BaseDTO(Long id, Boolean deleted) {
		super();
		this.id = id;
		this.deleted = deleted;

	}

	public BaseDTO(Long id, Date createdAt) {
		super();
		this.id = id;
		this.createdAt = createdAt;
	}

	public BaseDTO(Long id, Boolean deleted, Date createdAt) {
		super();
		this.id = id;
		this.deleted = deleted;
		this.createdAt = createdAt;
	}

	public BaseDTO(Long id, Date createdAt, Date modifiedAt) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public BaseDTO(Long id, Boolean deleted, Date createdAt, Date modifiedAt) {
		super();
		this.id = id;
		this.deleted = deleted;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}


}
