package com.example.AirportREST.exception;

public class AircraftNotFoundException extends Exception {
    public AircraftNotFoundException(String message) {
        super(message);
    }
}