package br.com.brasilct.codechallenge;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasilct.codechallenge.service.RouteService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { Application.class })
@Configuration
@ComponentScan("br.com.brasilct.codechallenge")
public class RouteServiceTest {

	@Autowired
	RouteService routeService;

	@Test
	@Transactional
	public void listAnyPathBetweenTest() {
		Assert.assertEquals(3, routeService.listAnyPathBetween(49l, 285l)
				.size());
	}

	@Test
	@Transactional
	public void listShortestPathBetweenTest() {
		Assert.assertEquals(3, routeService.listShortestPathBetween(49l, 285l).size());
	}

	@Test
	@Transactional
	public void calculateTimeFromShortestPathTest() {
		Assert.assertEquals(new Integer(15),
				routeService.calculateTimeFromShortestPath(49l, 285l));
	}

}
