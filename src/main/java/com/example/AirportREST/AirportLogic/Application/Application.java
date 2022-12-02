package com.example.AirportREST.AirportLogic.Application;


import com.example.AirportREST.AirportLogic.EventGenerator.EventGenerator;
import com.example.AirportREST.AirportLogic.Terminal.Terminal;
import com.example.AirportREST.AirportLogic.EventGenerator.logger.logger;
import com.example.AirportREST.exception.AircraftAlreadyExists;
import com.example.AirportREST.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

import java.sql.SQLException;
import java.util.Date;

import static java.lang.Thread.sleep;

@Service
public class Application {
    @Autowired
    private AircraftService aircraftService;

//    private AirportDBmySQL db;

    @Autowired
    private Terminal terminal;

    @Autowired
    private EventGenerator gen;

    private ArrayDeque<String> eventQueue;

    @Autowired
    public Application(AircraftService aircraftService) {
        aircraftService.eraseAll();
        this.aircraftService = aircraftService;
//        this.terminal=new Terminal();
        this.gen=new EventGenerator();
        this.eventQueue=new ArrayDeque<>();
    }

    @Scheduled(fixedRate = 3000)
    public void createEvent() throws AircraftAlreadyExists {
        gen.tryCreateRandomEvent(eventQueue);
    }

    @Scheduled(fixedRate = 3000)
    public void processEvent() {
        terminal.handleEvent(eventQueue);
    }


}
