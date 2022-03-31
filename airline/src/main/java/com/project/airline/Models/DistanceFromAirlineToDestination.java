package com.project.airline.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "distance_from_airline_to_destination")
public class DistanceFromAirlineToDestination {

	public DistanceFromAirlineToDestination(Long airlineId, Long destinationId, Double distance) {
		this.airlineId = airlineId;
		this.destinationId = destinationId;
		this.distance = distance;
	}

	public DistanceFromAirlineToDestination(Long airlineId, Long destinationId) {
		this.airlineId = airlineId;
		this.destinationId = destinationId;
	}
	
	public DistanceFromAirlineToDestination() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "airline_id")
	private Long airlineId;
	
	@Column(name = "destination_id")
	private Long destinationId;
	
	@Column(name = "distance")
	private Double distance;

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

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
	
}
