package br.com.brasilct.codechallenge.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
@NoArgsConstructor
public @Data class Station {
	
	@GraphId
	private Long id;
	private Double latitude;
	private Double longitude;
	@Indexed(indexType = IndexType.FULLTEXT, indexName = "name")
	private String name;
	private String displayName;
	private Double zone;
	private Integer totalLines;
	private Integer rall;
	
	@RelatedToVia(type="LEADS_TO", direction = Direction.BOTH)
	private Set<StationRelationship> stations;
	
	public void leadsTo(Station station2, Long lineId ){
		if (stations == null){
			stations = new HashSet<StationRelationship>();
		}
		StationRelationship satationRel = new StationRelationship(this, station2, lineId);
		stations.add(satationRel);
	}

}
