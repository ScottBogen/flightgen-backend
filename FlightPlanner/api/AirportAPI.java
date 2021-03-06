package com.bogen.FlightPlanner.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bogen.FlightPlanner.model.Airport;
import com.bogen.FlightPlanner.service.AirportService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("AirportAPI")
public class AirportAPI {
	
	@Autowired
	private AirportService airportService;
	
	@GetMapping("/getAirports")
	public ResponseEntity<?> getAirports(
			@RequestParam String method,
			@RequestParam String airportName,
			@RequestParam int minRange,
			@RequestParam int maxRange,
			@RequestParam String airportSize
			) {
		
		try {
			List<Airport> airports = airportService.getAirports(method, airportName, minRange, maxRange, airportSize);
			return new ResponseEntity<List<Airport>>(airports, HttpStatus.OK);
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
