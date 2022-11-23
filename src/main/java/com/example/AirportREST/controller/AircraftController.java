package com.example.AirportREST.controller;

import com.example.AirportREST.entity.AircraftEntity;
import com.example.AirportREST.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aircraft")
public class AircraftController {

    @Autowired
    private AircraftService aircraftService;

    @PostMapping
    public ResponseEntity addAircraft(@RequestBody AircraftEntity aircraftEntity){
        try {
            aircraftService.addAircraft(aircraftEntity);
            return ResponseEntity.ok("Added");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/findFreeParkingPlace")
    public ResponseEntity findFreeParkingPlace(){
        try {
            return ResponseEntity.ok(aircraftService.findFreeParkingPlace());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllAircraft(){
        try {
            return ResponseEntity.ok(aircraftService.findAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/ping")
    public ResponseEntity ping(){
        try {
            return ResponseEntity.ok("Live");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }
}
