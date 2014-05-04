package br.com.brasilct.codechallenge.controller;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.traversal.Evaluation;
import org.neo4j.graphdb.traversal.Evaluator;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Uniqueness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.brasilct.codechallenge.model.Station;
import br.com.brasilct.codechallenge.repository.StationRepository;

@Controller
public class RouteController {
	
	@Autowired StationRepository stationRepository; 
	
	@Autowired GraphDatabaseService graphDatabaseService;
	
	@Autowired Neo4jOperations neo4jOperations;

	@RequestMapping("/routes")
	@Transactional
	public @ResponseBody String getRoutes() {
		
		Transaction transaction = graphDatabaseService.beginTx();
		
		
		Station station1 = stationRepository.findByStationId(1l);
		
		TraversalDescription td = graphDatabaseService.traversalDescription()
		        .uniqueness( Uniqueness.NODE_PATH )
		        .evaluator( new Evaluator()
		{
		    @Override
		    public Evaluation evaluate( Path path )
		    {
		        return Evaluation.of( true, true );
		    }
		} ); 
		
		Iterable<Station> it = stationRepository.findAll(); 
		while (it.iterator().hasNext()){
		
			Station station = it.iterator().next();
			
			
			
		}
		
		
		String result = stationRepository.findAllByTraversal(station1, td).iterator().hasNext()+"";
		transaction.success();transaction.finish();
		return result;
	}
	
}