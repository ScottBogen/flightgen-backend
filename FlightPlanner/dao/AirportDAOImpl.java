package com.bogen.FlightPlanner.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bogen.FlightPlanner.entity.AirportDistanceEntity;
import com.bogen.FlightPlanner.entity.AirportEntity;
import com.bogen.FlightPlanner.model.Airport;

@Repository
public class AirportDAOImpl implements AirportDAO {

	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	public List<Airport> getAirports(String method, String airportName, int minRange, int maxRange, String airportSize) throws Exception {
		
		List<Airport> airports = new ArrayList<Airport>();
		AirportEntity userProvidedAirport = null;

		userProvidedAirport = searchDatabaseByICAO(airportName);
		if (userProvidedAirport == null) return null;
		
		airports.add(convertEntityToAirport(userProvidedAirport));
		
		List<AirportDistanceEntity> airportDistances = new ArrayList<AirportDistanceEntity>();
		airportDistances = searchDatabaseByAirportCoords(userProvidedAirport, minRange, maxRange, airportSize);
		
		for (int i = 0; i < airportDistances.size(); i++) {
			AirportDistanceEntity ade = (AirportDistanceEntity) airportDistances.get(i);
			AirportEntity ae = searchDatabaseByID(ade);
			Airport airport = convertEntityToAirport(ae);
			airports.add(airport);
		}
		
		return airports;
	}
	
	public AirportEntity searchDatabaseByID(AirportDistanceEntity ade) {	
		String queryString = "SELECT a from AirportEntity a WHERE a.airportId = :id";
		Query q = entityManager.createQuery(queryString);
		q.setParameter("id", ade.getAirportId());
		AirportEntity result = (AirportEntity) q.getSingleResult();
		return result;
	}
	
	public AirportEntity searchDatabaseByICAO(String gpsCode) {
		AirportEntity airportEntity = null;
	
		Query query = entityManager.createQuery("SELECT a FROM AirportEntity a WHERE a.gpsCode = :gpsCode").setParameter("gpsCode", gpsCode);
		airportEntity = (AirportEntity) query.getSingleResult();
		System.out.println("Latlong: " + airportEntity.getLatitudeDeg() + " " + airportEntity.getLongitudeDeg());
		
		return airportEntity;
	}
	
	public String convertAirportSizeString(String airportSize, int index) {
		switch (airportSize.charAt(index)) {
		case 'S':
			return "small_airport";
		case 'M':
			return "medium_airport";
		case 'L':
			return "large_airport";
		default:
			return "";
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<AirportDistanceEntity> searchDatabaseByAirportCoords(AirportEntity ae, int minRange, int maxRange, String airportSize) {
		// Step 3: Search for Airports in the DB by passing this Lat and Long
		
		List<AirportDistanceEntity> resultList = new ArrayList<AirportDistanceEntity>();
		List<AirportDistanceEntity> finalList = new ArrayList<AirportDistanceEntity>();

		// ex: SML
		String queryWhereClause = new String();
		int airportSizeIndex = 0;
		queryWhereClause = String.format("WHERE airport_type = '%s' ", convertAirportSizeString(airportSize, airportSizeIndex++));
		while (airportSizeIndex < airportSize.length()) {
			queryWhereClause += String.format("OR airport_type = '%s' ", convertAirportSizeString(airportSize, airportSizeIndex++));
		}
				
		String queryString = "SELECT airport_id, airport_name, (3959 * acos(cos(radians(:lat)) * cos(radians(latitude_deg)) "
				+ "    * cos( radians(longitude_deg) - radians(:long)) + sin(radians(:lat)) * sin(radians(latitude_deg)))) AS distance "
				+ "FROM Airport "
				+ queryWhereClause
				+ "HAVING distance > :minRange AND distance <= :maxRange "
				+ "ORDER BY distance";
		

		Query q = entityManager.createNativeQuery(queryString);
		q.setParameter("lat", ae.getLatitudeDeg());
		q.setParameter("long", ae.getLongitudeDeg());
		q.setParameter("minRange", minRange);
		q.setParameter("maxRange", maxRange);
		resultList = q.getResultList();
		
		if (resultList != null) {
			System.out.println("Number of results: " + resultList.size());
			
			Iterator it = resultList.iterator();
			while (it.hasNext()) {
				Object[] next = (Object[]) it.next();
				AirportDistanceEntity ade = new AirportDistanceEntity();
				int id = Integer.parseInt(String.valueOf(next[0]));
				ade.setAirportId(id);
				finalList.add(ade);
			}
		} 
		else {
			System.out.println("No results found!");
		}
		
		return finalList;
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

	@Override
	public List<Airport> findAirportsWithinDistance(String ident, int radius) {
		
		return null;
	}
	
}