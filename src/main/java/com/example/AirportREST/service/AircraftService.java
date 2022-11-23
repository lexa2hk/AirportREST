package com.example.AirportREST.service;

import com.example.AirportREST.entity.AircraftEntity;
import com.example.AirportREST.exception.AircraftAlreadyExists;
import com.example.AirportREST.exception.AircraftNotFoundException;
import com.example.AirportREST.repository.AircraftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AircraftService {

    @Autowired
    private AircraftRepo aircraftRepo;

    HashMap<Integer, Boolean> parkingMap = new HashMap<Integer, Boolean>();

    public AircraftService() {
        for(int i=0;i<26;i++) {
            parkingMap.put(i, false);
        }
    }

    public AircraftEntity addAircraft(AircraftEntity aircraftEntity) throws AircraftAlreadyExists {
        if(aircraftRepo.findByFlightcode(aircraftEntity.getFlightcode()) != null){
            throw new AircraftAlreadyExists("Aircraft with this number already exists");
        }
        return aircraftRepo.save(aircraftEntity);
    }

    public Iterable<AircraftEntity> findAll() throws AircraftNotFoundException {
        if(aircraftRepo.findAll() == null){
            throw new AircraftNotFoundException("No aircrafts found");
        }else{
            return aircraftRepo.findAll();
        }
    }


    public Integer findFreeParkingPlace() {
        return parkingMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(false))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public Integer claimParkingPlace(Integer parkingPlace) {
        parkingMap.put(parkingPlace, true);
        return parkingPlace;
    }

    public void eraseAll() {
        aircraftRepo.deleteAll();
    }
}
