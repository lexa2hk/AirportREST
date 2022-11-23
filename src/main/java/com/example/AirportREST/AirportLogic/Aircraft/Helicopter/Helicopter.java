package com.example.AirportREST.AirportLogic.Aircraft.Helicopter;

import com.example.AirportREST.AirportLogic.Aircraft.Aircraft;
import com.example.AirportREST.AirportLogic.Aircraft.Interface.AircraftOptions;
import com.example.AirportREST.AirportLogic.EventGenerator.logger.logger;

public class Helicopter extends Aircraft implements AircraftOptions, logger {
    public Helicopter(String status, String type, String model) {
        super(status, type, model);
    }

    @Override
    public String parking() {
        this.status= Status.PARKING;
        String message="Status of "+model+" has switched to: "+status;
        logger.log(message);
        return message;
    }

    @Override
    public String steering() {
        this.status= Status.STEERING;
        String message="Status of "+model+" has switched to: "+status;
        logger.log(message);
        return message;
    }

    @Override
    public String stopOnLane() {
        this.status= Status.STOP_ON_LANE;
        String message="Status of "+model+" has switched to: "+status;
        logger.log(message);
        return message;
    }

    @Override
    public String acceleration() {
        this.status= Status.ACCELERATION;
        String message="Status of "+model+" has switched to: "+status;
        logger.log(message);
        return message;
    }

    @Override
    public String takeoff() {
        this.status=Status.TAKEOFF;
        String message="Status of "+model+" has switched to: "+status;
        logger.log(message);
        return message;
    }

    @Override
    public String flight() {
        this.status= Status.FLIGHT;
        String message="Status of "+model+" has switched to: "+status;
        logger.log(message);
        return message;
    }

    @Override
    public String landing() {
        this.status= Status.LANDING;
        String message="Status of "+model+" has switched to: "+status;
        logger.log(message);
        return message;
    }

    @Override
    public String getInfo(){
        String message="Helicopter info: "+status+" "+type+" "+model;
        logger.log(message);
        return message;
    }
}
