package com.gil.hexagonalarchitectureddd.feature1.infrastructure.adapters.rest.data.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProductResponse {

	private Long id;

    private String name;

    private String description;
}
