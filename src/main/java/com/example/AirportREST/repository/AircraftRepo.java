package com.example.AirportREST.repository;

import com.example.AirportREST.entity.AircraftEntity;
import org.springframework.data.repository.CrudRepository;

public interface AircraftRepo extends CrudRepository<AircraftEntity, Integer> {

    AircraftEntity findByNum(String num);
}
