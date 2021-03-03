package com.bogen.FlightPlanner.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bogen.FlightPlanner.entity.AirportEntity;
import com.bogen.FlightPlanner.model.Airport;

@Repository
public class AirportDAOImpl implements AirportDAO {

	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	public List<Airport> getAirports() throws Exception {
		List<Airport> airports = new ArrayList<Airport>();
		List<AirportEntity> airportEntities = null;
		
		Query query = entityManager.createQuery("SELECT a FROM AirportEntity a");
		airportEntities = query.getResultList();
				
		for (AirportEntity entity : airportEntities) {
			Airport airport = convertEntityToAirport(entity);
			airports.add(airport);
		}
		
		return airports;
	}
	
	public Airport convertEntityToAirport(AirportEntity entity) {
		Airport airport = new Airport();
		airport.setAirportId(entity.getAirportId());
		airport.setAirportName(entity.getAirportName());
		airport.setAirportType(entity.getAirportType());
		airport.setContinent(entity.getContinent());
		airport.setElevation(entity.getElevation());
		airport.setGpsCode(entity.getGpsCode());
		airport.setHomeLink(entity.getHomeLink());
		airport.setIataCode(entity.getIataCode());
		airport.setIdent(entity.getIdent());
		airport.setIsoCountry(entity.getIsoCountry());
		airport.setIsoRegion(entity.getIsoRegion());
		airport.setKeywords(entity.getKeywords());
		airport.setLatitudeDeg(entity.getLatitudeDeg());
		airport.setLocalCode(entity.getLocalCode());
		airport.setLongitudeDeg(entity.getLongitudeDeg());
		airport.setMunicipality(entity.getMunicipality());
		airport.setScheduledService(entity.getScheduledService());
		airport.setWikipediaLink(entity.getWikipediaLink());
		return airport;
	}

	/* 
	 * Use case: 
	 * 		Airport and radius is entered by user (KSEA, 50)
	 * 		Call to the back end sends the desired Airport code (KSEA)
	 *		Back end calls DB to find KSEA's lat/long 		
	 * 		lat/long is returned to program
	 * 		program then calls DB once more to find airports within radius 
	 * 		API function returns list of airports to front-end
	 * */
	
	public List<Double> findCoordinatesViaIdentCode(String ident) {
		List<Double> coords = new ArrayList();
		AirportEntity airportEntity = null;
		
		Query query = entityManager.createQuery("SELECT a FROM AirportEntity a WHERE ident LIKE '" + ident + "'");
		airportEntity = (AirportEntity) query.getSingleResult();
		
		if (airportEntity == null) {
			return null;
		}
		
		coords.add(airportEntity.getLatitudeDeg());
		coords.add(airportEntity.getLongitudeDeg());
		
		return coords;
	}
	
	
	@Override
	public List<Airport> findAirportsWithinDistance(String ident, int radius) {
		
		
		
		
		return null;
	}
	
}