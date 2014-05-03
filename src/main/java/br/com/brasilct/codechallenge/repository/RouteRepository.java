package br.com.brasilct.codechallenge.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import br.com.brasilct.codechallenge.model.Route;

public interface RouteRepository extends GraphRepository<Route> {

}
