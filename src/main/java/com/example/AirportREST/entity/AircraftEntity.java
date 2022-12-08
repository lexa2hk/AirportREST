package com.example.AirportREST.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AircraftEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String city;
    private String time;
    private String status;
    private String aircrafttype;
    private String aircraftmodel;
    //parking section
    private Integer parkingplace;

    //status section
    private String flightcode;

    private String currentstatus;

    public AircraftEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAircrafttype() {
        return aircrafttype;
    }

    public void setAircrafttype(String aircrafttype) {
        this.aircrafttype = aircrafttype;
    }

    public String getAircraftmodel() {
        return aircraftmodel;
    }

    public void setAircraftmodel(String aircraftmodel) {
        this.aircraftmodel = aircraftmodel;
    }

    public Integer getParkingplace() {
        return parkingplace;
    }

    public void setParkingplace(Integer parkingplace) {
        this.parkingplace = parkingplace;
    }

    public String getFlightcode() {
        return flightcode;
    }

    public void setFlightcode(String flightcode) {
        this.flightcode = flightcode;
    }


    public String getCurrentstatus() {
        return currentstatus;
    }

    public void setCurrentstatus(String currentstatus) {
        this.currentstatus = currentstatus;
    }



}
