package br.com.brasilct.codechallenge;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasilct.codechallenge.model.Station;
import br.com.brasilct.codechallenge.repository.StationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
@Configuration
@ComponentScan("br.com.brasilct.codechallenge")
public class StationTest {

	@Autowired
	StationRepository repository;
	@Autowired GraphDatabaseService graphDatabaseService;
	
	@Test
	@Transactional
	public void addStationTest() {

		Assert.assertEquals(306, repository.count());

		Station station1 = new Station();
		station1.setStationId(999l);
		station1.setLatitude(0.0);
		station1.setLongitude(0.0);
		station1.setName("Station 1");
		station1.setDisplayName("Station 1");
		station1.setZone(1.0);
		station1.setTotalLines(1);
		station1.setRail(0);

		Station station2 = new Station();
		station2.setStationId(1000l);
		station2.setLatitude(0.0);
		station2.setLongitude(0.0);
		station2.setName("Station 2");
		station2.setDisplayName("Station 2");
		station2.setZone(1.0);
		station2.setTotalLines(1);
		station2.setRail(0);

		repository.save(station1);
		repository.save(station2);

		station1 = repository.findByStationId(station1.getStationId());
		station1.leadsTo(station2, 1l,graphDatabaseService);
		
		Assert.assertEquals(308, repository.count());

	}

}
