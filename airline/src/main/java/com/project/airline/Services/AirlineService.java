package com.project.airline.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.airline.Exceptions.AirLineExistException;
import com.project.airline.Exceptions.AirLineNotValidException;
import com.project.airline.Models.Aircraft;
import com.project.airline.Models.Airline;
import com.project.airline.Models.AvailableDestinationForAirline;
import com.project.airline.Models.Destination;
import com.project.airline.Models.DistanceFromAirlineToDestination;
import com.project.airline.repositories.AirlineRepository;

@Service
public class AirlineService {

	@Autowired
	AirlineRepository airlineRepository;

	@Autowired
	DestinationService destinationService;

	@Autowired
	AircraftService aircraftService;

	public Airline createAirline(Airline p_airline) throws Exception {
		try {
			Boolean isValidInput = checkInputIsValid(p_airline);
			if (isValidInput) {
				Boolean existAirline = airlineRepository.findExistByName(p_airline.getName());
				if (!existAirline) {
					Airline newAirline = airlineRepository.save(new Airline(p_airline.getName(),
							p_airline.getInitialBudget(), p_airline.getCurrentBalance(),
							p_airline.getHomeBaseLatitude(), p_airline.getHomeBaseLongitude()));
					return newAirline;
				} else {
					throw new AirLineExistException( p_airline.getName());
				}
			} else {
				throw new AirLineNotValidException();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private Boolean checkInputIsValid(Airline p_airline) {
		if (p_airline.getCurrentBalance() == null || 
				p_airline.getHomeBaseLatitude() == null || 
				p_airline.getHomeBaseLongitude() == null || 
				p_airline.getInitialBudget() == null ||
				p_airline.getName() == null)
			return false;
		else
			return true;
	}

	public Object[] getAirlinesAndCurrentBalance() {
		try {
			Object[] airLines = airlineRepository.getAirlinesAndCurrentBalance();
			return airLines;
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Destination> calcDestinationForAirline(Long airlineId) {

		List<Destination> availableDestinations = new ArrayList<Destination>();

		List<DistanceFromAirlineToDestination> distanceFromAirlineToDestinations = destinationService
				.getAllDistanceFromAirlineToDestinations();
		List<Aircraft> aircrafts = aircraftService.getAircraftByAirlineId(airlineId);

		// destinationService.deleteByAirlineId(airlineId);

		for (DistanceFromAirlineToDestination distance : distanceFromAirlineToDestinations) {
			Double dist = distance.getDistance();
			for (Aircraft aircraft : aircrafts) {
				if (dist > aircraft.getMaxDistance()) {
					AvailableDestinationForAirline availableDestination = destinationService
							.insertIntoAvailableDestination(airlineId, distance.getDestinationId(), aircraft.getId());
					availableDestinations
							.add(destinationService.getDestinationyd(availableDestination.getDestinationId()));
				}
			}
		}
		return availableDestinations;
	}
}
