package com.cts.multiplexmoviebooking.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

import com.cts.multiplexmoviebooking.dtos.BookingDTO;
import com.cts.multiplexmoviebooking.dtos.ShowCheckDTO;
import com.cts.multiplexmoviebooking.models.Hall;
import com.cts.multiplexmoviebooking.services.BookingService;

@CrossOrigin
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired private BookingService service;
	
	@PostMapping
    public ResponseEntity<?> saveHall(@RequestBody BookingDTO dto){
        service.save(dto);
        Map<String,String> response = new HashMap<>();
        response.put("msg","Booking saved successfully");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<?> listall(Optional<Integer> userid){
    	if(userid.isPresent())
    		return ResponseEntity.ok(service.alluserbooking(userid.get()));
        return ResponseEntity.ok(service.listall());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> cancelBooking(@PathVariable("id") int id){
        service.delete(id);
        Map<String,String> response = new HashMap<>();
        response.put("msg","Booking cancelled successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findDetails(@PathVariable("id") int id){
        return ResponseEntity.ok().body(service.findById(id));
    }
    
    @GetMapping("check")
    public ResponseEntity<?> findByShow(ShowCheckDTO dto){
        return ResponseEntity.ok().body(service.allOccupiedBookings(dto));
    }
}
