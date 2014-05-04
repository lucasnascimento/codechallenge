package br.com.brasilct.codechallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.brasilct.codechallenge.model.Station;
import br.com.brasilct.codechallenge.service.RouteService;

@Controller
public class RouteController {

	@Autowired RouteService routeService;
	
	@RequestMapping("/route/{stationOrigin}/{stationDestination}")
	public @ResponseBody List<Station> getAnyPath(@PathVariable Long stationOrigin,@PathVariable Long stationDestination) {
		
		return routeService.listAnyPathBetween(stationOrigin, stationDestination);
	}
	
	@RequestMapping("/route/{stationOrigin}/{stationDestination}/shortest")
	public @ResponseBody List<Station> getShortestPath(@PathVariable Long stationOrigin,@PathVariable Long stationDestination) {
		
		return routeService.listShortestPathBetween(stationOrigin, stationDestination);
	}

	@RequestMapping("/route/{stationOrigin}/{stationDestination}/shortest/totaltime")
	public @ResponseBody Integer getTimeFromShortestPath(@PathVariable Long stationOrigin,@PathVariable Long stationDestination) {
		
		return routeService.calculateTimeFromShortestPath(stationOrigin, stationDestination);
	}
}