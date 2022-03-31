package com.project.airline.Models;

import javax.persistence.*;

@Entity
@Table(name = "airline")
public class Airline {

	public  Airline() {};
	
	public Airline(String name, 
			Double initialBudget, 
			Double currentBalance, 
			Double homeBaseLatitude,
			Double homeBaseLongitude) {
		this.name = name;
		this.initialBudget = initialBudget;
		this.currentBalance = currentBalance;
		this.homeBaseLatitude = homeBaseLatitude;
		this.homeBaseLongitude = homeBaseLongitude;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="initial_budget")
	private Double initialBudget;
	
	@Column(name="current_balance")
	private Double currentBalance;
	
	@Column(name="home_base_latitude")
	private Double homeBaseLatitude;
	
	@Column(name="home_base_longitude")
	private Double homeBaseLongitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getInitialBudget() {
		return initialBudget;
	}

	public void setInitialBudget(Double initialBudget) {
		this.initialBudget = initialBudget;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Double getHomeBaseLatitude() {
		return homeBaseLatitude;
	}

	public void setHomeBaseLatitude(Double homeBaseLatitude) {
		this.homeBaseLatitude = homeBaseLatitude;
	}

	public Double getHomeBaseLongitude() {
		return homeBaseLongitude;
	}

	public void setHomeBaseLongitude(Double homeBaseLongitude) {
		this.homeBaseLongitude = homeBaseLongitude;
	}
	
	
}
