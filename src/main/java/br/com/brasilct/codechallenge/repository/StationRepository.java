package br.com.brasilct.codechallenge.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import br.com.brasilct.codechallenge.model.Station;

public interface StationRepository extends GraphRepository<Station> {
	
	Station findByStationId(Long id);

}
