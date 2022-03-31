package com.project.airline.Services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.spatial.util.GeoDistanceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.project.airline.Models.Airline;
import com.project.airline.Models.AvailableDestinationForAirline;
import com.project.airline.Models.Destination;
import com.project.airline.Models.DistanceFromAirlineToDestination;
import com.project.airline.repositories.AirlineRepository;
import com.project.airline.repositories.AvailableDestinationForAirlineRepository;
import com.project.airline.repositories.DestinationRepository;
import com.project.airline.repositories.DistanceFromAirlineToDestinationRepository;

@Service
public class DestinationService {

	DecimalFormat dtime = new DecimalFormat("#.##");

	@Autowired
	AirlineRepository airlineRepository;

	@Autowired
	DestinationRepository destinationRepository;

	@Autowired
	AvailableDestinationForAirlineRepository availableDestinationForAirlineRepository;

	@Autowired
	DistanceFromAirlineToDestinationRepository distanceFromAirlineToDestinationRepository;

	public Destination insertDestination(Destination p_destination) {
		try {
			Destination newDestination = destinationRepository.save(new Destination(p_destination.getName(),
					p_destination.getDestinationLatitude(), p_destination.getDestinationLongitude()));
			return newDestination;
		} catch (Exception e) {
			return null;
		}
	}

	public List<DistanceFromAirlineToDestination> calcDistanceToDestinations(Long p_airlineId) {
		List<DistanceFromAirlineToDestination> result = new ArrayList<DistanceFromAirlineToDestination>();
		Airline airline = airlineRepository.getById(p_airlineId);
		List<Destination> destinations = destinationRepository.findAll();

		List<Long> destinationsId = getDestinationIdsFromList(destinations);
		destinationRepository.deleteAllById(destinationsId);

		for (Destination destination : destinations) {
			double distance = GeoDistanceUtils.haversin(airline.getHomeBaseLatitude(), airline.getHomeBaseLongitude(),
					destination.getDestinationLatitude(), destination.getDestinationLongitude()) / 1000;
			distance = Double.valueOf(dtime.format(distance));
			if (distance > 0) {
				DistanceFromAirlineToDestination res = distanceFromAirlineToDestinationRepository
						.save(new DistanceFromAirlineToDestination(p_airlineId, destination.getId(), distance));
				result.add(res);
			}
		}
		return result;
	}

	private List<Long> getDestinationIdsFromList(List<Destination> p_destinations) {
		List<Long> ids = new ArrayList<Long>();

		for (Destination destination : p_destinations) {
			ids.add(destination.getId());
		}

		return ids;
	}

	public List<DistanceFromAirlineToDestination> getAllDistanceFromAirlineToDestinations() {
		return distanceFromAirlineToDestinationRepository.findAll();
	}

	public AvailableDestinationForAirline insertIntoAvailableDestination(Long p_airlineId, Long p_destinationId,
			Long p_aircraftId) {
		AvailableDestinationForAirline availableDestination = availableDestinationForAirlineRepository
				.save(new AvailableDestinationForAirline(p_airlineId, p_destinationId, p_aircraftId));
		return availableDestination;

	}

	public Destination getDestinationyd(Long p_destinationId) {
		return destinationRepository.findById(p_destinationId).get();
	}

	@Modifying
	public void deleteByAirlineId(Long airlineId) {
		availableDestinationForAirlineRepository.deleteByAirlineId(airlineId);
		
	}

}
