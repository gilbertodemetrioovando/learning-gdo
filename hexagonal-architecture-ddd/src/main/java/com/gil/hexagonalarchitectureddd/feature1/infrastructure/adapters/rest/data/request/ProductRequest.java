package com.gil.hexagonalarchitectureddd.feature1.infrastructure.adapters.rest.data.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequest {

	@NotEmpty(message = "Name may not be empty")
	private String name;

	@NotEmpty(message = "Description may not be empty")
	private String description;
}
