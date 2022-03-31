package com.project.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.airline.Models.DistanceFromAirlineToDestination;

public interface DistanceFromAirlineToDestinationRepository
		extends JpaRepository<DistanceFromAirlineToDestination, Long> {
	@Query("SELECT d FROM DistanceFromAirlineToDestination d WHERE d.airlineId = ?1 and d.destinationId = ?2")
	DistanceFromAirlineToDestination findByAirlineToDestination(Long airlineId, Long destinationId);
	
	@Query("SELECT d FROM DistanceFromAirlineToDestination d WHERE d.airlineId = ?1 ")
	DistanceFromAirlineToDestination findByAirline(Long airlineId);
}
