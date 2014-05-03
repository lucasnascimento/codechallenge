package br.com.brasilct.codechallenge.model;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NodeEntity
@AllArgsConstructor
@NoArgsConstructor
public @Data
class Route {

	@GraphId
	private Long id;
	
	private Long line;
	private String name;
	private String colour;
	private String stripe;

}
