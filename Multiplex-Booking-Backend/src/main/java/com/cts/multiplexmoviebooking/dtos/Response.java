package com.cts.multiplexmoviebooking.dtos;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Response {
    public static ResponseEntity<?> success(String data) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", data);
        return ResponseEntity.ok(map);
    }

    public static ResponseEntity<?> error(String err) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg",err);
        return ResponseEntity.badRequest().body(map);
    }

    public static ResponseEntity<?> status(HttpStatus status) {
        return ResponseEntity.status(status).build();
    }
}
