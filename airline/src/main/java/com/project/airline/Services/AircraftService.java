package com.project.airline.Services;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.airline.Models.Aircraft;
import com.project.airline.repositories.AircraftRepository;

@Service
public class AircraftService {

	@Autowired
	AircraftRepository aircraftRepository;
	
	public Aircraft createAircraft(Aircraft p_aircraft) {
		try {
			Aircraft newAircraft = aircraftRepository.save(new Aircraft(p_aircraft.getAirlineId(),
					p_aircraft.getMaxDistance(), p_aircraft.getPrice(), p_aircraft.getAircraftActivityDts()));
			return newAircraft;
		} catch (Exception e) {
			throw e;
		}
	}

	public Aircraft sellAircraft(Long p_aircraftId, Long p_buyerAirlineId) {
		try {
			Aircraft dbAircraft = aircraftRepository.findById(p_aircraftId).get();
			if (dbAircraft != null) {
				int diff = Period.between(dbAircraft.getAircraftActivityDts(), LocalDate.now()).getMonths();

				Double price = dbAircraft.getPrice() * (1 - diff * 0.02);
				if (price <= 0) {
					price = dbAircraft.getPrice();
				}
				dbAircraft.setAirlineId(p_buyerAirlineId);
				dbAircraft.setPrice(price);
				aircraftRepository.save(dbAircraft);
				return dbAircraft;
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Aircraft> getAircraftByAirlineId(Long airlineId) {
		return aircraftRepository.findAllByAirlineId(airlineId);
	}
	
//	public Aircraft sellAircraft(SellAircraftInput p_sellAircraftInput) {
//		try {
//			Aircraft dbAircraft = aircraftRepository.findById(p_sellAircraftInput.getAircraftId()).get();
//			if (dbAircraft != null) {
//				int diff = Period.between(dbAircraft.getAircraftActivityDts(), LocalDate.now()).getMonths();
//
//				Double price = dbAircraft.getPrice() * (1 - diff * 0.02);
//				if (price <= 0) {
//					price = dbAircraft.getPrice();
//				}
//				dbAircraft.setAirlineId(p_sellAircraftInput.getBuyerAirlineId());
//				dbAircraft.setPrice(price);
//				aircraftRepository.save(dbAircraft);
//				return dbAircraft;
//			} else {
//				return null;
//			}
//		} catch (Exception e) {
//			throw e;
//		}
//
//	}
}
