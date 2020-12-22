package com.photwo.app.PhotwoAppAccountManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @GetMapping(path = "/status")
    public String status() {
        return "UP";
    }
}
