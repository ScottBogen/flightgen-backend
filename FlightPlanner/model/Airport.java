package com.bogen.FlightPlanner.model;

public class Airport {
	private int airportId;
	private String ident;
	private String airportType;
	private String airportName;
	private double latitudeDeg;
	private double longitudeDeg;
	private int elevation;
	private String continent;
	private String isoCountry;
	private String isoRegion;
	private String municipality;
	private String scheduledService;
	private String gpsCode;
	private String iataCode;
	private String localCode;
	private String homeLink;
	private String wikipediaLink;
	private String keywords;
	
	// getters and setters
	public int getAirportId() {
		return airportId;
	}
	public void setAirportId(int airportId) {
		this.airportId = airportId;
	}
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	public String getAirportType() {
		return airportType;
	}
	public void setAirportType(String airportType) {
		this.airportType = airportType;
	}
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public double getLatitudeDeg() {
		return latitudeDeg;
	}
	public void setLatitudeDeg(double latitudeDeg) {
		this.latitudeDeg = latitudeDeg;
	}
	public double getLongitudeDeg() {
		return longitudeDeg;
	}
	public void setLongitudeDeg(double longitudeDeg) {
		this.longitudeDeg = longitudeDeg;
	}
	public int getElevation() {
		return elevation;
	}
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getIsoCountry() {
		return isoCountry;
	}
	public void setIsoCountry(String isoCountry) {
		this.isoCountry = isoCountry;
	}
	public String getIsoRegion() {
		return isoRegion;
	}
	public void setIsoRegion(String isoRegion) {
		this.isoRegion = isoRegion;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getScheduledService() {
		return scheduledService;
	}
	public void setScheduledService(String scheduledService) {
		this.scheduledService = scheduledService;
	}
	public String getGpsCode() {
		return gpsCode;
	}
	public void setGpsCode(String gpsCode) {
		this.gpsCode = gpsCode;
	}
	public String getIataCode() {
		return iataCode;
	}
	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}
	public String getLocalCode() {
		return localCode;
	}
	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}
	public String getHomeLink() {
		return homeLink;
	}
	public void setHomeLink(String homeLink) {
		this.homeLink = homeLink;
	}
	public String getWikipediaLink() {
		return wikipediaLink;
	}
	public void setWikipediaLink(String wikipediaLink) {
		this.wikipediaLink = wikipediaLink;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

}
