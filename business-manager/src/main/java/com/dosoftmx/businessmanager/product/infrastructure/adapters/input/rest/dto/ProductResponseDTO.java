package com.dosoftmx.businessmanager.product.infrastructure.adapters.input.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductResponseDTO {

	private Long id;

    private String name;

    private String description;
}
