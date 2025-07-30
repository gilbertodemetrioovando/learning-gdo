package com.gil.hexagonalarchitectureddd.feature2.application.ports.outbound.repositories.presistence;

import java.util.List;

import com.gil.hexagonalarchitectureddd.feature2.domain.model.BookingDomainEntity;

public interface BookingRepositoryOutboundPort {

	List<BookingDomainEntity> findAll();

	void save(BookingDomainEntity entity);

}
