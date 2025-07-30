package com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.outbound.repositories.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.outbound.repositories.mysql.entities.BookingEntity;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

}
