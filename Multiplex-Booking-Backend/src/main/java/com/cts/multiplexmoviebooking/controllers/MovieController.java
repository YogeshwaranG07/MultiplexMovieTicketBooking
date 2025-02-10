package com.cts.multiplexmoviebooking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cts.multiplexmoviebooking.dtos.MovieDTO;
import com.cts.multiplexmoviebooking.models.Movie;
import com.cts.multiplexmoviebooking.services.MovieService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/movies")
public class MovieController {
	@Autowired private MovieService mService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveMovie(@ModelAttribute Movie dto, @RequestParam(required = false)MultipartFile photo){
        mService.save(dto,photo);
        Map<String,String> response = new HashMap<>();
        response.put("msg","Movie saved successfully");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<?> listall(){
        return ResponseEntity.ok(mService.listall());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") int id){
        mService.deleteMovie(id);
        Map<String,String> response = new HashMap<>();
        response.put("msg","Movie deleted successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findDetails(@PathVariable("id") int id){
        return ResponseEntity.ok().body(mService.findById(id));
    }
}
