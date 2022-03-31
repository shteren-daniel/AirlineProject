package com.project.airline.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "available_destination_for_airline")
public class AvailableDestinationForAirline {

	public AvailableDestinationForAirline(Long airlineId, Long destinationId, Long aircraftId) {
		this.airlineId = airlineId;
		this.destinationId = destinationId;
		this.aircraftId = aircraftId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "airline_id")
	private Long airlineId;
	
	@Column(name = "aircraft_id")
	private Long aircraftId;
	
	@Column(name = "destination_id")
	private Long destinationId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
	}

	public Long getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(Long destinationId) {
		this.destinationId = destinationId;
	}

	public Long getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(Long aircraftId) {
		this.aircraftId = aircraftId;
	}
}
