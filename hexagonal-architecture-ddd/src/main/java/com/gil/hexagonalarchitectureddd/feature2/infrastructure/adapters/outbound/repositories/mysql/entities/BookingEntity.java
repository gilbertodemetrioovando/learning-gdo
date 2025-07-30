package com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.outbound.repositories.mysql.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class BookingEntity implements Serializable{
	
	private static final long serialVersionUID = -2891986129572835210L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long userId;
	private long totalAmount;
	private String pickUpAddress;
	private String dropOffAddress;
	private LocalDateTime createdOn;
}
