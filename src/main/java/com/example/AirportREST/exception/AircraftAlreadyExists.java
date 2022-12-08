package com.example.AirportREST.exception;

import java.io.Serializable;

public class AircraftAlreadyExists extends Exception implements Serializable {
    public AircraftAlreadyExists(String message) {
        super(message);
    }
}
