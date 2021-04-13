package com.bogen.FlightPlanner.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bogen.FlightPlanner.dao.AirportDAO;
import com.bogen.FlightPlanner.model.Airport;

@Service(value="airportService")
@Transactional
public class AirportServiceImpl implements AirportService {

	@Autowired
	AirportDAO airportDAO;
	
	@Override
	public List<Airport> getAirports(String method, String airportName, int minRange, int maxRange, String airportSize) {
		List<Airport> airports = null;		
		try {
			airports = airportDAO.getAirports(method, airportName, minRange, maxRange, airportSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return airports;
	}


	
	@Override
	public List<Airport> findAirportsWithinDistance(String ident, int radius) {
		List<Airport> airports = null;
		
		try {
			airports = airportDAO.findAirportsWithinDistance(ident, radius);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return airports;
	}
}
