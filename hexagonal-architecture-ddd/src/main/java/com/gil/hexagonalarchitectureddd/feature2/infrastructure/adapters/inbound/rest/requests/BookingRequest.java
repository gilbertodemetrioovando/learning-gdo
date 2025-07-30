package com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.inbound.rest.requests;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingRequest {

	private long id;
	private long userId;
	private long totalAmount;
	private String pickUpAddress;
	private String dropOffAddress;
}
