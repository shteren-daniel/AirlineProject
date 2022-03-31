package com.project.airline.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.airline.Exceptions.AirLineExistException;
import com.project.airline.Models.Airline;
import com.project.airline.Models.Destination;
import com.project.airline.Services.AirlineService;
import com.project.airline.repositories.AirlineRepository;

@RestController
@RequestMapping("/airline")
public class AirlineController {

	@Autowired
	AirlineService airlineService;

	public AirlineController(AirlineRepository airlineRepository) {
		airlineRepository.save(new Airline("elal", 100.00, 100.00, 32.000454, 34.870743));
		airlineRepository.save(new Airline("arkia", 200.0, 200.0, 29.555616, 34.953570));
	}

	@CrossOrigin
	@PostMapping("/createAirline")
	public ResponseEntity<Object> createAirline(@RequestBody Airline p_airline) throws Exception {
		try {
			Airline newAirline = airlineService.createAirline(p_airline);
			return ResponseEntity.ok(newAirline);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@GetMapping("getAirlinesAndCurrentBalance")
	public ResponseEntity<Object> getAirlinesAndCurrentBalance() {
		try {
			Object[] airLines = airlineService.getAirlinesAndCurrentBalance();
			if (airLines.length > 0) {
				return ResponseEntity.ok(airLines);
			} else {
				throw new Exception("No Airlines in system");
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("calcDestinationForAirline")
	public ResponseEntity<Object> calcDestinationForAirline(@RequestParam(value = "airlineId") String airlineId) {
		try {
			Long id = Long.parseLong(airlineId);
			List<Destination> activeDestinations = airlineService.calcDestinationForAirline(id);
			return ResponseEntity.ok(activeDestinations);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
