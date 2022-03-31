package com.project.airline.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.airline.Models.Destination;
import com.project.airline.Models.DistanceFromAirlineToDestination;
import com.project.airline.Services.DestinationService;
import com.project.airline.repositories.DestinationRepository;

@RestController
@RequestMapping("/destination")
public class DestinationController {

	public DestinationController(DestinationRepository destinationRepository) {
		destinationRepository.save(new Destination("NY", 40.714353, -74.005973));
		destinationRepository.save(new Destination("LONDON", 51.510000, 0.060000));
	}

	@Autowired
	DestinationService destinationService;

	@PostMapping("/insertDestination")
	public ResponseEntity<Object> insertDestination(@RequestBody Destination p_destination) {
		try {
			Destination newDestination = destinationService.insertDestination(p_destination);
			return ResponseEntity.ok(newDestination);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/calcDistanceToDestinationsForAirline")
	public ResponseEntity<Object> calcDistanceToDestinations(@RequestParam(value = "airlineId") String airlineId) {
		try {
			Long id = Long.parseLong(airlineId);
			List<DistanceFromAirlineToDestination> result = destinationService.calcDistanceToDestinations(id);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
