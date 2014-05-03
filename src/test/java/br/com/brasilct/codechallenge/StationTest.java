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

		Assert.assertEquals(0, repository.count());

		Station station1 = new Station();
		station1.setStationId(1l);
		station1.setLatitude(51.5028);
		station1.setLongitude(-0.2801);
		station1.setName("Acton Town");
		station1.setDisplayName("Acton<br />Town");
		station1.setZone(3.0);
		station1.setTotalLines(2);
		station1.setRail(0);

		Station station2 = new Station();
		station2.setStationId(2l);
		station2.setLatitude(51.5143);
		station2.setLongitude(-0.0755);
		station2.setName("Aldgate");
		station2.setDisplayName(null);
		station2.setZone(1.0);
		station2.setTotalLines(2);
		station2.setRail(0);

		repository.save(station1);
		repository.save(station2);

		station1 = repository.findByStationId(station1.getStationId());
		station1.leadsTo(station2, 1l,graphDatabaseService);
		
		repository.save(station1);
		Assert.assertEquals(2, repository.count());
		

	}

}
