package br.com.brasilct.codechallenge.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;
import org.springframework.data.neo4j.support.index.IndexType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NodeEntity
@NoArgsConstructor
@EqualsAndHashCode(exclude={"stations"})
public @Data class Station {
	
	@JsonIgnore
	@GraphId
	private Long id;
	
	private Long stationId;
	private Double latitude;
	private Double longitude;
	@Indexed(indexType = IndexType.FULLTEXT, indexName = "name")
	private String name;
	private String displayName;
	private Double zone;
	private Integer totalLines;
	private Integer rail;
	
	@JsonIgnore
	@RelatedToVia(type="LEADS_TO", direction = Direction.OUTGOING)
	private Set<StationRelationship> stations = new HashSet<StationRelationship>();
	
	@SuppressWarnings("deprecation")
	public StationRelationship leadsTo(Station station2, Long lineId,GraphDatabaseService graphDatabaseService ){
		StationRelationship stationRel = new StationRelationship(this, station2, lineId);
		Transaction transaction = graphDatabaseService.beginTx();
		stations.add(stationRel);
		transaction.success();transaction.finish();
		return stationRel;
	}

}
