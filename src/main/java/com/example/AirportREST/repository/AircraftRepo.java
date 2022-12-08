package com.example.AirportREST.repository;

import com.example.AirportREST.entity.AircraftEntity;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface AircraftRepo extends CrudRepository<AircraftEntity, Integer>, Serializable {
    AircraftEntity findByFlightcode(String flightcode);
}
