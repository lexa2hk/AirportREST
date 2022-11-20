package com.example.AirportREST.service;

import com.example.AirportREST.entity.AircraftEntity;
import com.example.AirportREST.exception.AircraftAlreadyExists;
import com.example.AirportREST.repository.AircraftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AircraftService {

    @Autowired
    private AircraftRepo aircraftRepo;

    public AircraftEntity addAircraft(AircraftEntity aircraftEntity) throws AircraftAlreadyExists {
        if(aircraftRepo.findByNum(aircraftEntity.getNum()) != null){
            throw new AircraftAlreadyExists("Aircraft with this number already exists");
        }
        return aircraftRepo.save(aircraftEntity);
    }

    public Iterable<AircraftEntity> findAll() throws NullPointerException{
        try{
            Iterable<AircraftEntity> ob = aircraftRepo.findAll();
            return ob;
        }catch (Exception e) {
            throw new NullPointerException("No aircrafts found MSG: " + e.getMessage());
        }
    }
}
