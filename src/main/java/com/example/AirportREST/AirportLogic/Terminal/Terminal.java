package com.example.AirportREST.AirportLogic.Terminal;

//import Aircraft.Aircraft;
//import AirportDB.AirportDBmySQL;
//import logger.logger;
//import AirportDB.FlightNote;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

import com.example.AirportREST.AirportLogic.Aircraft.Aircraft;
import com.example.AirportREST.AirportLogic.EventGenerator.logger.logger;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Iterator;

public class Terminal implements logger {


    private enum Status{OPEN, CLOSE};
    private Status status;
    private int peopleCurrent=0;
//    protected AirportDBmySQL database;
    private int totalEvents=0;
    public Terminal(String status){
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

    public void addEvent(String flightCode, String eventType, String city, Aircraft aircraft) throws SQLException {
        String num=Integer.toString(totalEvents);
        this.totalEvents+=1;
        Date time=new Date();
        Timestamp dateOfEvent=new Timestamp(time.getTime());
        //FlightNote note=new FlightNote(num,type,city,time.toString(),aircraft.status.toString(),aircraft.type.toString(),aircraft.model);
//        database.pasteAircraftStatus(flightCode, eventType, city, dateOfEvent, aircraft.status.toString(), aircraft.type.toString(), aircraft.model);

    }

    public void handleEvent(ArrayDeque<String> eventQueue) throws SQLException {
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
