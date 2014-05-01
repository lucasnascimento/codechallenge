package br.com.brasilct.codechallenge.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

@RelationshipEntity
@NoArgsConstructor
public @Data
class StationRelationship {

	@GraphId
	private Long id;
	
	@StartNode private Station station1;
	@EndNode private Station station2;
	private Long routeId;
	
	public StationRelationship(Station station1, Station station2, Long routeId){
		this.station1 = station1;
		this.station2 = station2;
		this.routeId = routeId;
	}

}
