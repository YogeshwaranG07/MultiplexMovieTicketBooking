package com.cts.multiplexmoviebooking.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.multiplexmoviebooking.config.JwtTokenUtil;
import com.cts.multiplexmoviebooking.dtos.AuthResponse;
import com.cts.multiplexmoviebooking.dtos.LoginDTO;
import com.cts.multiplexmoviebooking.dtos.Response;
import com.cts.multiplexmoviebooking.models.User;
import com.cts.multiplexmoviebooking.services.UserService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UsersController {

	@Autowired private UserService service;
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @PostMapping(value = "register",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody User user){
        if(service.CheckIfExists(user.getEmail())){
            return ResponseEntity.badRequest().body("Email already exists");
        }
        service.saveUser(user);
        Map<String,String> response = new HashMap<>();
        response.put("msg","User registered successfully");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping(value = "/validate",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> validate(@RequestBody LoginDTO dto){
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getUserid(), dto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails user = (UserDetails) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);

            AuthResponse response = new AuthResponse();
            response.setAccessToken(accessToken);

            User tuser = service.findByEmailId(authentication.getName()).get();
            response.setUserid(tuser.getUserid());
            response.setUserName(tuser.getUserName());
            response.setEmail(tuser.getEmail());
            response.setAdmin(authentication.getAuthorities().toArray()[0].toString() == "ADMIN");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}", dto.getUserid(), e);
            return Response.error("Invalid username or password");
        }

    }

    @GetMapping
    public ResponseEntity<?> listall(){
        return ResponseEntity.ok(service.listall());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findDetails(@PathVariable("id") int id){
        return ResponseEntity.ok().body(service.findById(id));
    }
}
