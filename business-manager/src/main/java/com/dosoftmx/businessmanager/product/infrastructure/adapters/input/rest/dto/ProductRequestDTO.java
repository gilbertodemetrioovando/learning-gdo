package com.dosoftmx.businessmanager.product.infrastructure.adapters.input.rest.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequestDTO {

	@NotEmpty(message = "Name may not be empty")
	private String name;

	@NotEmpty(message = "Description may not be empty")
	private String description;
}
