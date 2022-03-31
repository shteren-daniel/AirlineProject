package com.project.airline.Models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aircraft")
public class Aircraft {

	public Aircraft() {};

	public Aircraft(Long airlineId, Double price, Double maxDistance, LocalDate aircraftActivityDts) {
		this.airlineId = airlineId;
		this.price = price;
		this.maxDistance = maxDistance;
		this.aircraftActivityDts = aircraftActivityDts;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "airline_id")
	private Long airlineId;

	@Column(name = "price")
	private Double price;

	@Column(name = "max_distance")
	private Double maxDistance;

	@Column(name = "aircraft_activity_dts")
	private LocalDate aircraftActivityDts;

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(Double maxDistance) {
		this.maxDistance = maxDistance;
	}

	public LocalDate getAircraftActivityDts() {
		return aircraftActivityDts;
	}

	public void setAircraftActivityDts(LocalDate aircraftActivityDts) {
		this.aircraftActivityDts = aircraftActivityDts;
	}

}
