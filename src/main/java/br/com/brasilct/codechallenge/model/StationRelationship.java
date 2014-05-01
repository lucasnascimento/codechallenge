package br.com.brasilct.codechallenge.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.StartNode;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data
class StationRelationship {
	
	@StartNode
	private Station station1;
	@EndNode
	private Station station2;
	private Long routeId;

}
