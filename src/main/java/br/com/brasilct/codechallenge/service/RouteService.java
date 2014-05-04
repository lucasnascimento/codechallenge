package br.com.brasilct.codechallenge.service;

import java.util.LinkedList;
import java.util.List;

import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphalgo.WeightedPath;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.PathExpanders;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Service;

import br.com.brasilct.codechallenge.model.RelTypes;
import br.com.brasilct.codechallenge.model.Station;
import br.com.brasilct.codechallenge.repository.StationRepository;

@Service
public class RouteService {
	
	@Autowired StationRepository stationRepository; 
	
	@Autowired GraphDatabaseService graphDatabaseService;
	
	@Autowired Neo4jOperations neo4jOperations;
	
	static final PathFinder<WeightedPath> PATH_FINDER = GraphAlgoFactory.dijkstra(
			PathExpanders.forTypeAndDirection(RelTypes.LEADS_TO,
					Direction.OUTGOING), "distance");
	
	@SuppressWarnings("deprecation")
	public List<Station> listAnyPathBetween(Long stationOrigin, Long stationDestination){
		Transaction transaction = graphDatabaseService.beginTx();
		
		Node nodeStation1 = getStationNode(stationOrigin);
		Node nodeStation2 = getStationNode(stationDestination);

		List<Station> resultList = new LinkedList<Station>();
		Iterable<WeightedPath> paths = PATH_FINDER.findAllPaths(nodeStation1, nodeStation2);
		for (WeightedPath pathw : paths){
			for (Node n : pathw.nodes()) {
				resultList.add( stationRepository.findOne(n.getId()));
			}
			break;//As business ask to "any" path, I picked the first one
		}
		transaction.success();transaction.finish();
		
		return resultList;
	}
	
	@SuppressWarnings("deprecation")
	public List<Station> listShortestPathBetween(Long stationOrigin, Long stationDestination){
		Transaction transaction = graphDatabaseService.beginTx();

		
		WeightedPath path = getWeightPath(stationOrigin, stationDestination);
		
		List<Station> resultList = new LinkedList<Station>();
		if (path != null){
			for (Node n : path.nodes()) {
				resultList.add( stationRepository.findOne(n.getId()));
			}
		}
		transaction.success();transaction.finish();

		
		return resultList;
	}

	private WeightedPath getWeightPath(Long stationOrigin, Long stationDestination) {
		Node nodeStation1 = getStationNode(stationOrigin);
		Node nodeStation2 = getStationNode(stationDestination);

		WeightedPath path = PATH_FINDER.findSinglePath(nodeStation1, nodeStation2);
		
		return path;
	}

	private Node getStationNode(Long stationOrigin) {
		Station station1 = stationRepository.findByStationId(stationOrigin);
		Node nodeStation1 = neo4jOperations.getNode(station1.getId());
		return nodeStation1;
	}
	
	@SuppressWarnings("deprecation")
	public Integer calculateTimeFromShortestPath(Long stationOrigin, Long stationDestination){
		Transaction transaction = graphDatabaseService.beginTx();
		WeightedPath path = getWeightPath(stationOrigin, stationDestination);
		
		int totalTime = 0 ;
		if (path != null){
			Iterable<Node> itNodes = path.nodes();
			
			if (itNodes.iterator().hasNext()){
				
				Node startNode = itNodes.iterator().next();			
				long lastLine = 0;
				
				for(Node node: itNodes){
					if (node != startNode){
						Relationship r = neo4jOperations.getRelationshipBetween(startNode, node, "LEADS_TO");
						
						long lineId = (Long) r.getProperty("routeId");
						
						if (lastLine == 0){
							lastLine = lineId;
						}
						if (lastLine == lineId){
							totalTime += 3;
						}else {
							totalTime += 12;
							lastLine = lineId;
						}
						startNode = node;
					}
				}
			}
		}
		transaction.success();transaction.finish();

		return totalTime;
	}

	
}
