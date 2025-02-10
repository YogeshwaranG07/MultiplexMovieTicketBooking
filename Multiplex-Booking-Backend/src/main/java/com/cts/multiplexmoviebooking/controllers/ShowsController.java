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

import com.cts.multiplexmoviebooking.dtos.SearchDTO;
import com.cts.multiplexmoviebooking.dtos.ShowDTO;
import com.cts.multiplexmoviebooking.services.ShowsService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/shows")
public class ShowsController {
	@Autowired private ShowsService mService;
	

    @PostMapping
    public ResponseEntity<?> saveShow(@RequestBody ShowDTO dto){
        mService.save(dto);
        Map<String,String> response = new HashMap<>();
        response.put("msg","Show saved successfully");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<?> listall(){
        return ResponseEntity.ok(mService.listall());
    }
    
    @GetMapping("todays")
    public ResponseEntity<?> todayShows(){
        return ResponseEntity.ok(mService.todayShows());
    }
    
    @GetMapping("search")
    public ResponseEntity<?> searchShows(SearchDTO dto){
        return ResponseEntity.ok(mService.searchShows(dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") int id){
        mService.deleteShow(id);
        Map<String,String> response = new HashMap<>();
        response.put("msg","Show deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findDetails(@PathVariable("id") int id){
        return ResponseEntity.ok().body(mService.findById(id));
    }
}
