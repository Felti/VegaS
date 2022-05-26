package com.vega.app.dtos;

import java.util.Date;
import java.util.Set;

import com.vega.app.dtos.simple.SimpleInvoiceDTO;
import com.vega.app.dtos.simple.SimpleOrderDTO;
import com.vega.app.dtos.simple.SimpleProductDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class OrderDTO extends SimpleOrderDTO {

	private SimpleInvoiceDTO invoice;

	private Set<SimpleProductDTO> products;

	public OrderDTO(Long id, Boolean deleted, Date createdAt, Date modifiedAt, String reference, Boolean isCancelled,
			Boolean isPaid, Boolean isShipped, Double totalAmount, Long customerId, String customerFirstName,
			String customerLastName, String customerPhoneNumber, String customerEmail) {
		super(id, deleted, createdAt, modifiedAt, reference, isCancelled, isPaid, isShipped, totalAmount, customerId,
				customerFirstName, customerLastName, customerPhoneNumber, customerEmail);
	}

}
