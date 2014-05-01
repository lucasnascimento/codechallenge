package br.com.brasilct.codechallenge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RouteController {
	

	@RequestMapping("/routes")
	public @ResponseBody String getHome() {
		return "routes";
	}
	
}