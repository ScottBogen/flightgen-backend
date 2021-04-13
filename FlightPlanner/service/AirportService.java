package com.bogen.FlightPlanner.service;

import java.util.List;

import com.bogen.FlightPlanner.model.Airport;

public interface AirportService {
	public List<Airport> getAirports(String method, String airportName, int minRange, int maxRange, String airportSize);
	public List<Airport> findAirportsWithinDistance(String ident, int radius);
}
