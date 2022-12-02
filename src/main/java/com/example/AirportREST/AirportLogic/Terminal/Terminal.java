package com.example.AirportREST.AirportLogic.Terminal;

//import Aircraft.Aircraft;
//import AirportDB.AirportDBmySQL;
//import logger.logger;
//import AirportDB.FlightNote;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

import com.example.AirportREST.AirportLogic.Aircraft.Aircraft;
import com.example.AirportREST.AirportLogic.Aircraft.Airplane.Airplane;
import com.example.AirportREST.AirportLogic.EventGenerator.logger.logger;
import com.example.AirportREST.entity.AircraftEntity;
import com.example.AirportREST.exception.AircraftAlreadyExists;
import com.example.AirportREST.repository.AircraftRepo;
import com.example.AirportREST.service.AircraftService;
import com.example.AirportREST.service.AircraftTermService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Component
public class Terminal implements logger {

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private AircraftRepo aircraftRepo;


    private enum Status{OPEN, CLOSE};
    private Status status;
    private int peopleCurrent=0;
//    protected AirportDBmySQL database;
    private int totalEvents=0;

    //create fields which show is landing line is free or not
    private boolean landingLineFree=true;

    //create map which show taxiways is free or not
    private HashMap<String, Boolean> taxiways = new HashMap<String,Boolean>();






    public Terminal() {
        taxiways.put("A", true);
        taxiways.put("B", true);
        taxiways.put("C", true);
        taxiways.put("D", true);
        taxiways.put("E", true);
        taxiways.put("M", true);
        taxiways.put("PARK", true);



        String status="OPEN";
        switch (status){
            case "OPEN":
                this.status=Status.OPEN;
                break;
            case "CLOSE":
                this.status=Status.CLOSE;
                break;
            default:
                String message="UNDEFINED STATUS OF TERMINAL";
                logger.log(message);
                break;
        }
    }

    //create method which will return array of correct taxiways
    //after landing go A -> M -> PARK if places are 0-17 and it takes 10 seconds
    //after landing go A -> M -> C -> PARK if places are 18-26 and it takes 15 seconds
    //on takeoff go PARK -> C -> M -> D -> A if places are 18-26 and it takes 15 seconds
    //on takeoff go PARK -> B -> M -> A if places are 0-17 and it takes 10 seconds
    public String[] getCorrectTaxiways(String type, int places){
        String[] taxiways;
        if(type.equals("LANDING")){
            if(places>=0 && places<=17){
                taxiways=new String[]{"10","A", "M", "PARK"};
            }
            else if(places>=18 && places<=26){
                taxiways=new String[]{"15","A", "M", "C", "PARK"};
            }
            else{
                taxiways=new String[]{"15","A", "M", "C", "D", "PARK"};
            }
        }
        else if(type.equals("TAKEOFF")){
            if(places>=0 && places<=17){
                taxiways=new String[]{"10","PARK", "B", "M", "A"};
            }
            else{
                taxiways=new String[]{"10","PARK", "C", "M", "D"};
            }
        }
        else{
            taxiways=null;
        }
        return taxiways;
    }



    public void addEvent(String flightCode, String eventType, String city, Aircraft aircraft) throws AircraftAlreadyExists {
        String num=Integer.toString(totalEvents);
        this.totalEvents+=1;
        Date time=new Date();
        Timestamp dateOfEvent=new Timestamp(time.getTime());
        //FlightNote note=new FlightNote(num,type,city,time.toString(),aircraft.status.toString(),aircraft.type.toString(),aircraft.model);
//        database.pasteAircraftStatus(flightCode, eventType, city, dateOfEvent, aircraft.status.toString(), aircraft.type.toString(), aircraft.model);

        AircraftEntity aircraftEntity=new AircraftEntity();
        aircraftEntity.setType(eventType);
        aircraftEntity.setCity(city);
        aircraftEntity.setTime(time.toString());
        aircraftEntity.setStatus(aircraft.status.toString());
        aircraftEntity.setAircrafttype(aircraft.type.toString());
        aircraftEntity.setAircraftmodel(aircraft.model);
        aircraftEntity.setFlightcode(flightCode);

        if(aircraft.status.toString().equals("FLIGHT")){
            aircraftEntity.setCurrentstatus("IN_AIR");
        }else{
            aircraftEntity.setCurrentstatus("PARKED");
        }

        Integer parkingPlace=aircraftService.findFreeParkingPlace();

        if(parkingPlace!=null) {
            aircraftEntity.setParkingplace(parkingPlace);
            aircraftService.claimParkingPlace(parkingPlace);
            aircraftService.addAircraft(aircraftEntity);
        }





    }

    public void handleEvent(ArrayDeque<String> eventQueue){
//        queue contains flightcodes which
        // method will handle events from queue
        // 1. get flightcode from queue, if final state then remove from queue
        // 2. get aircraft from database
        // 3. do something with aircraft
        // 4. update database for this aircraft
        // 5. set time which will be taken by this event
        // 6. remove flightcode from queue
        // 7. add flightcode to end of queue

        if(eventQueue.isEmpty()){
            return;
        }
        String flightCode=eventQueue.getFirst();
        logger.log(eventQueue.toString());

        AircraftEntity aircraft=aircraftRepo.findByFlightcode(flightCode);
        if(aircraft==null){
            return;
        }
        String[] taxiways=getCorrectTaxiways(aircraft.getType(),aircraft.getParkingplace());
        String message = "";

        if(aircraft.getType().equals("LANDING")) {
            if (aircraft.getStatus().equals("FLIGHT")) {
                if (landingLineFree) {
                    landingLineFree = false;
                    aircraft.setStatus("LANDING");
                    aircraftRepo.save(aircraft);

                    eventQueue.removeFirst();
                    eventQueue.addLast(flightCode);
                    landingLineFree = true;
                    message = "Aircraft " + flightCode + " landed";
                }
                //add message to database
                message = aircraft.getFlightcode() + " is waiting for landing line";
            } else if (aircraft.getStatus().equals("LANDING")) {
                message = aircraft.getFlightcode() + " is taxiing by path ";
                for (int i = 0; i < taxiways.length; i++) {
                    message += taxiways[i] + " ";
                }
                aircraft.setStatus("TAXIING");
                aircraftRepo.save(aircraft);

                eventQueue.removeFirst();
                eventQueue.addLast(flightCode);
            }else if(aircraft.getStatus().equals("TAXIING")){
                message = aircraft.getFlightcode() + " is parked on place " + aircraft.getParkingplace();
                aircraft.setStatus("PARKED");
                aircraftRepo.save(aircraft);
                eventQueue.removeFirst();
                //remove aircraft from database
                aircraftRepo.delete(aircraft);
            }

        }
        else if(aircraft.getType().equals("TAKEOFF")){
            if(aircraft.getStatus().equals("PARKING")){
                message = aircraft.getFlightcode() + " is taxiing by path ";
                for (int i = 0; i < taxiways.length; i++) {
                    message += taxiways[i] + " ";
                }
                aircraft.setStatus("TAXIING");
                aircraftRepo.save(aircraft);
                eventQueue.removeFirst();
                eventQueue.addLast(flightCode);
            }
            else if(aircraft.getStatus().equals("TAXIING")){
                if(landingLineFree){
                    landingLineFree=false;
                    message = aircraft.getFlightcode() + " is taking off";
                    aircraft.setStatus("FLIGHT");
                    aircraftRepo.save(aircraft);
                    eventQueue.removeFirst();
                    eventQueue.addLast(flightCode);
                    landingLineFree=true;
                }
                else{
                    message = aircraft.getFlightcode() + " is waiting for takeoff line";
                }
            }else if(aircraft.getStatus().equals("FLIGHT")){
                message = aircraft.getFlightcode() + " is leaving zone. Goodbye!";
                eventQueue.removeFirst();
                //remove aircraft from database
                aircraftRepo.delete(aircraft);

            }

        }else{
            message = "Unknown event type";
        }
        logger.log(message);


    }

//    public void deleteEvent(String num){
//        database.delete(num);
//    }
}
