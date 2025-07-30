package com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gil.hexagonalarchitectureddd.feature2.application.ports.inbound.BookingCommandInboundPort;
import com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.BookingMapper;
import com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.outbound.repositories.mysql.BookingRepository;
import com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.outbound.repositories.mysql.BookingRepositoryAdapter;

@Configuration
public class BeanConfigurationFeature2 {

	@Bean
	public BookingMapper bookingMapper(ModelMapper mapper) {
		return new BookingMapper(mapper);
	}

	@Bean
	public BookingRepositoryAdapter bookingRepositoryAdapter(BookingRepository bookingRepository, 
			BookingMapper bookingMapper) {
		return new BookingRepositoryAdapter(bookingRepository, bookingMapper);
	}

	@Bean
	public BookingCommandInboundPort bookingCommandInboundPort(BookingRepositoryAdapter bookingRepositoryAdapter) {
		return new BookingCommandInboundPort(bookingRepositoryAdapter);
	}
	
}
