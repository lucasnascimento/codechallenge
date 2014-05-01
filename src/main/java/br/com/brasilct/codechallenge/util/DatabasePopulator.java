package br.com.brasilct.codechallenge.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;

import lombok.extern.log4j.Log4j;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import br.com.brasilct.codechallenge.model.Route;
import br.com.brasilct.codechallenge.model.Station;
import br.com.brasilct.codechallenge.model.StationRelationship;
import br.com.brasilct.codechallenge.repository.RouteRepository;
import br.com.brasilct.codechallenge.repository.StationRepository;

@Service 
@Log4j
public class DatabasePopulator implements ApplicationListener<ContextRefreshedEvent>{
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		try {
			populateFromCSV();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Autowired StationRepository stationRepository;
	
	@Autowired RouteRepository routeRepository;
	
	@Value(value="csv_data_path")
	private String csvDataPath;

	public void populateFromCSV() throws IOException{
		loadStations();
		loadRoutes();
		loadLines();
	}

	@Autowired GraphDatabaseService graphDatabaseService;
	
	public void loadLines() throws IOException {
		Reader in = new FileReader("/Users/lucas/Development/workspace-lnprojetos/codechallenge/data/lines.csv");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader("station1","station2","line").parse(in);
		for (CSVRecord record : records) {
			if (record.isConsistent()){
				try {
					
					Long station1Id = Long.parseLong(record.get("station1"));
					Long station2Id = Long.parseLong(record.get("station2"));
					Long lineId = Long.parseLong(record.get("line"));

					Station station1 = stationRepository.findByStationId(station1Id);
					Station station2 = stationRepository.findByStationId(station2Id);
					
					if (station1.getStations() == null){
						station1.setStations(new HashSet<StationRelationship>());
					}
					stationRepository.save(station1);

					
					station1.leadsTo(station2, lineId,graphDatabaseService);
					
					
					
				} catch(NumberFormatException e) {
					log.error(e);
				}
			}
		}
		
	}

	public void loadRoutes() throws IOException {
		Reader in = new FileReader("/Users/lucas/Development/workspace-lnprojetos/codechallenge/data/routes.csv");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader("line","name","colour","stripe").parse(in);
		for (CSVRecord record : records) {
			if (record.isConsistent()){
				try {
					Route route = new Route();
					
					route.setLine(Long.parseLong(record.get("line")));
					route.setName(record.get("name"));
					route.setColour(record.get("colour"));
					route.setStripe(record.get("stripe"));
					
					routeRepository.save(route);
				} catch(NumberFormatException e) {
					log.error(e);
				}
			}
		}
	}

	public void loadStations() throws IOException {
		Reader in = new FileReader("/Users/lucas/Development/workspace-lnprojetos/codechallenge/data/stations.csv");
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader("id","latitude","longitude","name","display_name","zone","total_lines","rail").parse(in);
		for (CSVRecord record : records) {
			if (record.isConsistent()){
				try {
					Station station = new Station();
					station.setStationId(Long.parseLong(record.get("id")));
					station.setLatitude(Double.parseDouble(record.get("latitude")));
					station.setLongitude(Double.parseDouble(record.get("longitude")));
					station.setName(record.get("name"));
					station.setDisplayName(record.get("display_name"));
					station.setZone(Double.parseDouble(record.get("zone")));
					station.setTotalLines(Integer.parseInt(record.get("total_lines")));
					station.setRail(Integer.parseInt(record.get("rail")));
					stationRepository.save(station);
				} catch(NumberFormatException e) {
					log.error(e);
				}
			}
		}
	}


	
	
}
