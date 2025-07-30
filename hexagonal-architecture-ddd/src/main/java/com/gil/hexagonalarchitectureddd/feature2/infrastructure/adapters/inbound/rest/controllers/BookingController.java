package com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.inbound.rest.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gil.hexagonalarchitectureddd.feature2.application.ports.inbound.BookingCommandInboundPort;
import com.gil.hexagonalarchitectureddd.feature2.domain.model.BookingDomainEntity;
import com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.BookingMapper;
import com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.inbound.rest.requests.BookingRequest;
import com.gil.hexagonalarchitectureddd.feature2.infrastructure.adapters.inbound.rest.responses.BookingResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/feature2")
@RequiredArgsConstructor
public class BookingController {
	
	private final BookingCommandInboundPort bookingCommandInboundPort;

	private final BookingMapper bookingMapper;

	@PostMapping(value = "/bookings", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request) {
		bookingCommandInboundPort.save(bookingMapper.toBookingDomainEntity(request));
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/bookings", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<BookingDomainEntity>> getAllBooking() {
		
		return new ResponseEntity<>(this.bookingCommandInboundPort.findAll(), HttpStatus.OK);
	}
}
