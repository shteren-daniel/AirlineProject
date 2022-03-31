package com.project.airline.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.airline.Models.AvailableDestinationForAirline;
import com.project.airline.Models.Airline;
import com.project.airline.Models.Destination;

public interface AvailableDestinationForAirlineRepository extends JpaRepository<AvailableDestinationForAirline, Long> {
	List<Destination> findDestinationsByAirlineId(Long airlineId);
	List<Airline> findAirlinesByDestinationId(Long airlineId);

	//@Query("DELETE FROM AvailableDestinationForAirline a WHERE a.airlineId = ?1 ")
	
	@Query("DELETE FROM AvailableDestinationForAirline a WHERE a.airlineId =:airlineId")
	void deleteByAirlineId(Long airlineId);
}
