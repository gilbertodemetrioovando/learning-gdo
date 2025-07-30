package com.gil.hexagonalarchitectureddd.feature2.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BookingDomainEntity implements Serializable{

	private static final long serialVersionUID = 4152732175344911226L;
	private long id;
	private long userId;
	private long totalAmount;
	private String pickUpAddress;
	private String dropOffAddress;
	private LocalDateTime createdOn;
}
