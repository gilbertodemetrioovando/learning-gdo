package com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.gil.hexagonalarchitectureddd.feature2.domain.model.BookingDomainEntity;
import com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.inbound.rest.requests.BookingRequest;
import com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.outbound.repositories.mysql.entities.BookingEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookingMapper {

    private final ModelMapper mapper;
    
    

    public BookingDomainEntity toBookingDomainEntity(BookingRequest entity){
        return mapper.map(entity, BookingDomainEntity.class);
    }

	public BookingEntity toBookingJPAEntity(BookingDomainEntity entity) {
		return mapper.map(entity, BookingEntity.class);
	}

	public List<BookingDomainEntity> toListBookingDomainDTO(List<BookingEntity> entities){
		List<BookingDomainEntity> list = new ArrayList<>();
		entities.forEach(entity -> 
			list.add(mapper.map(entity, BookingDomainEntity.class))
    	);
        return list;
	}

}
