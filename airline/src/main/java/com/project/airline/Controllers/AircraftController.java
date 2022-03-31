package com.project.airline.Controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.airline.Models.Aircraft;
import com.project.airline.Services.AircraftService;
import com.project.airline.repositories.AircraftRepository;

@RestController
@RequestMapping("/aircraft")
public class AircraftController {

	public AircraftController(AircraftRepository aircraftRepository) {
		aircraftRepository.save(new Aircraft((long) 2, 1000.0, 500.0, LocalDate.of(2022, 1, 1)));
	}

	@Autowired
	AircraftService aircraftService;

	
	@PostMapping("/insertAircraft")
	public ResponseEntity<Object> createAircraft(@RequestBody Aircraft p_aircraft) {
		try {
			Aircraft newAircraft = aircraftService.createAircraft(p_aircraft);
			return ResponseEntity.ok(newAircraft);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	
	@PostMapping("/sellAircraft")
	public ResponseEntity<Object> sellAircraft(@RequestParam(value = "aircraftId") Long aircraftId,
			@RequestParam(value = "buyerAirlineId") Long buyerAirlineId) {
		try {
			Aircraft updatedAircraft = aircraftService.sellAircraft(aircraftId, buyerAirlineId);
			return ResponseEntity.ok(updatedAircraft);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
