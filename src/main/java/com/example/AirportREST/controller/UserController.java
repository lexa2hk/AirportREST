package com.example.AirportREST.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/users")
public class UserController implements Serializable {

    @GetMapping
    public ResponseEntity getUsers(){
        try {
            return ResponseEntity.ok("Live");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error");
        }
    }

}
