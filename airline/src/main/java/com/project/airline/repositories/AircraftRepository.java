package com.project.airline.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.airline.Models.Aircraft;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
	  Optional<Aircraft> findById(Long id);

	  @Query("SELECT a FROM Aircraft a where a.airlineId = ?1")
	List<Aircraft> findAllByAirlineId(Long airlineId);
	}
