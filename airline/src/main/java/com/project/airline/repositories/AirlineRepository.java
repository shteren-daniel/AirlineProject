package com.project.airline.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.airline.Models.Airline;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
	Optional<Airline> findById(Long id);

	List<Airline> findByName(String name);

	@Query("SELECT a.name, a.currentBalance FROM Airline a")
	Object[] getAirlinesAndCurrentBalance();

	@Query("select count(a.id) >0 FROM Airline a where a.name = ?1 ")
	boolean findExistByName(String name);
}
