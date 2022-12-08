package com.example.AirportREST.exception;

import java.io.Serializable;

public class AircraftNotFoundException extends Exception implements Serializable {
    public AircraftNotFoundException(String message) {
        super(message);
    }
}