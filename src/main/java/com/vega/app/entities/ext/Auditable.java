package com.vega.app.entities.ext;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable implements Serializable {

	private static final long serialVersionUID = -240871556654702312L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@CreatedDate
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "deleted")
	private Boolean deleted;

	@Column(name = "deleted_at")
	private LocalDateTime deletedAt;

	// Supply the ID of the user that deleted the row
	@Column(name = "deleted_by")
	private Long deletedBy;

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private Long createdBy;

	@LastModifiedBy
	@Column(name = "modified_by")
	private Long modifiedBy;

	public Auditable() {
		this.deleted = Boolean.FALSE;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + ", deleted=" + deleted
				+ ", deletedAt=" + deletedAt + ", deletedBy=" + deletedBy + "}";
	}

}
