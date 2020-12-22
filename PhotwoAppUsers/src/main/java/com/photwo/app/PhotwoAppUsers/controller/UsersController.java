package com.photwo.app.PhotwoAppUsers.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UsersController {

    @GetMapping(path = "/status")
    public String status() {
        return "UP";
    }
}
