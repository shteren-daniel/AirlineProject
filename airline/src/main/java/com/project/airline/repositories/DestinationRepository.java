package com.project.airline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.airline.Models.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

}
