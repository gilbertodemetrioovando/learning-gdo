package com.gil.hexagonalarchitectureddd.feature2.application.ports.inbound;

import java.util.List;


import com.gil.hexagonalarchitectureddd.feature2.application.ports.outbound.repositories.presistence.BookingRepositoryOutboundPort;
import com.gil.hexagonalarchitectureddd.feature2.domain.model.BookingDomainEntity;
import com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.outbound.repositories.mysql.entities.BookingEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class BookingCommandInboundPort {
	private final BookingRepositoryOutboundPort bookingRepositoryOutboundPort;


	public void save(BookingDomainEntity entity) {
		log.info("Saving BookingDomainEntity");
		bookingRepositoryOutboundPort.save(entity);
	}
	
	public List<BookingDomainEntity> findAll() {
		log.info("Saving BookingDomainEntity");
		return bookingRepositoryOutboundPort.findAll();
	}
}
