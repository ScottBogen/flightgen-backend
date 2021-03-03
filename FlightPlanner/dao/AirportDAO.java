package com.bogen.FlightPlanner.dao;

import java.util.List;

import com.bogen.FlightPlanner.model.Airport;

public interface AirportDAO {
	public List<Airport> getAirports() throws Exception;

	public List<Airport> findAirportsWithinDistance(String ident, int radius);

}
