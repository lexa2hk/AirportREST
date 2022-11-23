package com.example.AirportREST.AirportLogic.Terminal;

//import Aircraft.Aircraft;
//import AirportDB.AirportDBmySQL;
//import logger.logger;
//import AirportDB.FlightNote;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

import com.example.AirportREST.AirportLogic.Aircraft.Aircraft;
import com.example.AirportREST.AirportLogic.EventGenerator.logger.logger;
import com.example.AirportREST.entity.AircraftEntity;
import com.example.AirportREST.exception.AircraftAlreadyExists;
import com.example.AirportREST.service.AircraftService;
import com.example.AirportREST.service.AircraftTermService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Iterator;

@Component
public class Terminal implements logger {

    @Autowired
    private AircraftTermService aircraftService;


    private enum Status{OPEN, CLOSE};
    private Status status;
    private int peopleCurrent=0;
//    protected AirportDBmySQL database;
    private int totalEvents=0;


    public Terminal() {
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

        aircraftService.addAircraft(aircraftEntity);


    }

    public void handleEvent(ArrayDeque<String> eventQueue){
//        изначально очерень пуста, поэтому логично будет заполнять ее на этапе генерации как то
//        String flightCode=eventQueue.pollFirst();
//        JSONArray event=database.selectStatus("aircraftstatus", "FlightCode", flightCode);
//        JSONArray event=database.selectStatus("aircraftstatus", "CurrentAircraftStatus", "FLIGHT");
        String eventType;
        String currentAircraftStatus;
//        if (!event.isEmpty()){
//            JSONObject obj = (JSONObject) event.get(0);
//            System.out.println(obj.toJSONString());
//            eventType= (String) obj.get("EventType");
//            currentAircraftStatus= (String) obj.get("CurrentAircraftStatus");
//
//            System.out.println(currentAircraftStatus);
//            //Доделать управление самолетомз
//        }
//        else {
//            System.out.println("Event queue is empty");
//        }
    }

//    public void deleteEvent(String num){
//        database.delete(num);
//    }
}
