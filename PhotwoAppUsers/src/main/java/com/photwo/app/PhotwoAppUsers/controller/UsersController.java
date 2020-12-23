package com.photwo.app.PhotwoAppUsers.controller;

import com.photwo.app.PhotwoAppUsers.model.User;
import com.photwo.app.PhotwoAppUsers.service.UsersService;
import com.photwo.app.PhotwoAppUsers.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = "/users")
public class UsersController {

    @Autowired
    private Environment env;

    @Autowired
    UsersServiceImpl usersServiceImpl;

    @GetMapping(path = "/status")
    public ResponseEntity<String> status() {
        String response = "Working on port: " + env.getProperty("local.server.port");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        user.setUserId(UUID.randomUUID().toString());
        usersServiceImpl.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Iterable<User> findAll() {
        return usersServiceImpl.findAll();
    }
}
