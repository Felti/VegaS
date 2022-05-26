package com.vega.app.dtos.simple;

import java.util.Date;

import org.apache.commons.lang3.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vega.app.dtos.BaseDTO;
import com.vega.app.dtos.UserDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SimpleOrderDTO extends BaseDTO {

	private String reference;

	private Boolean isCancelled;

	private Boolean isPaid;

	private Boolean isShipped;

	private Double totalAmount;
	
	private SimpleUserDTO customer;
	
	private String customerCompleteName;

	public SimpleOrderDTO(Long id, Boolean deleted, Date createdAt, Date modifiedAt, String reference, Boolean isCancelled, Boolean isPaid, Boolean isShipped,
			Double totalAmount, Long customerId, String customerFirstName, String customerLastName, String customerPhoneNumber, String customerEmail) {
		super(id,deleted,createdAt,modifiedAt);
		this.reference = reference;
		this.isCancelled = isCancelled;
		this.isPaid = isPaid;
		this.isShipped = isShipped;
		this.totalAmount = totalAmount;
		this.customer = new UserDTO(customerId,customerFirstName,customerLastName,customerPhoneNumber,customerEmail);
		this.customerCompleteName = getFullName();
	}
	
	@JsonIgnore
	public String getFullName() {
		var sb = new StringBuilder();

		if (ObjectUtils.isNotEmpty(customer.getFirstName())) {
			sb.append(customer.getFirstName());
			sb.append(" ");
			sb.append(ObjectUtils.isEmpty(customer.getLastName()) ? "" : customer.getLastName());
		} else sb.append(ObjectUtils.isEmpty(customer.getLastName()) ? "" : customer.getLastName());

		return sb.toString().trim();
	}
	
	
	

}
