package com.example.rest.controllers;

import com.example.rest.models.User;
import com.example.rest.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthenticationRestControllerForUser {
    @Autowired
    private UserServices userServices;


    @GetMapping("/api/incomingUser")
    public ResponseEntity<User> getAuthenticationUser(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(userServices.getAuthenticationUser(user), HttpStatus.OK);

    }

}
