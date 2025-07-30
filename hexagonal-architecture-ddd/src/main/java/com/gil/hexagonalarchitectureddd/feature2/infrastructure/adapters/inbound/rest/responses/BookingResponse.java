package com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.inbound.rest.responses;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingResponse implements Serializable{
	private static final long serialVersionUID = 6932250368930722104L;
	private long id;
	private long userId;
	private long totalAmount;
	private String pickUpAddress;
	private String dropOffAddress;
	private LocalDateTime createdOn;
}
