package br.com.brasilct.codechallenge.model;

import org.springframework.data.neo4j.annotation.GraphId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data
class Route {

	@GraphId
	private Long id;
	private String name;
	private String colour;
	private String stripe;

}
