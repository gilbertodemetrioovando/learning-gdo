package com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.outbound.repositories.mysql;

import java.util.List;


import com.gil.hexagonalarchitectureddd.feature2.application.ports.outbound.repositories.presistence.BookingRepositoryOutboundPort;
import com.gil.hexagonalarchitectureddd.feature2.domain.model.BookingDomainEntity;
import com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.BookingMapper;
import com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.outbound.repositories.mysql.entities.BookingEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookingRepositoryAdapter implements BookingRepositoryOutboundPort {

	private final BookingRepository bookingRepository;
	private final BookingMapper bookingMapper;

	@Override
	public List<BookingDomainEntity> findAll() {
		List<BookingEntity> entities = bookingRepository.findAll();
		return bookingMapper.toListBookingDomainDTO(entities);
	}

	@Override
	public void save(BookingDomainEntity entity) {
		// Transform Aggregate Root 's states into JPA Entity 's states before saving
		// them into database
		bookingRepository.save(bookingMapper.toBookingJPAEntity(entity));
	}
}
