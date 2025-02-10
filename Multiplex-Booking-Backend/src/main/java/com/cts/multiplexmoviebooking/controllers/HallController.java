package com.cts.multiplexmoviebooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.multiplexmoviebooking.dtos.HallCapacityDTO;
import com.cts.multiplexmoviebooking.models.Hall;
import com.cts.multiplexmoviebooking.services.HallService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/halls")
public class HallController {
	@Autowired private HallService service;

    @PostMapping("seats")
    public ResponseEntity<?> saveHallSeats(@RequestBody HallCapacityDTO hc){
        service.saveCapacity(hc);
        Map<String,String> response = new HashMap<>();
        response.put("msg","Hall Seats saved successfully");
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("seats/{id}")
    public ResponseEntity<?> deleteSeat(@PathVariable("id") int id){
        service.deleteSeat(id);
        Map<String,String> response = new HashMap<>();
        response.put("msg","Seat deleted successfully");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<?> saveHall(@RequestBody Hall hall){
        service.save(hall);
        Map<String,String> response = new HashMap<>();
        response.put("msg","Hall saved successfully");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<?> listall(){
        return ResponseEntity.ok(service.listall());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteHall(@PathVariable("id") int id){
        service.deleteHall(id);
        Map<String,String> response = new HashMap<>();
        response.put("msg","Hall deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findDetails(@PathVariable("id") int id){
        return ResponseEntity.ok().body(service.findById(id));
    }
}
