package com.bogen.FlightPlanner.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class AirportDistanceEntity {
	
	@Id
	@Column(name="airport_id")
	private int airportId;
	
	@Column(name="airport_name")
	private String airportName;
	
	@Column(name="distance")
	private int distance;

	public int getAirportId() {
		return airportId;
	}

	public void setAirportId(int airportId) {
		this.airportId = airportId;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		distance = distance;
	}
	
}
