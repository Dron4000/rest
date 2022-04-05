package com.example.rest.controllers;

import com.example.rest.models.Role;
import com.example.rest.services.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class RestControllerForAllRoles {
    @Autowired
    private RoleServices roleServices;

    @GetMapping("api/roles")
    public ResponseEntity<Set<Role>> getAllRoles() {
        return new ResponseEntity<>(roleServices.getAllRoles(), HttpStatus.OK);
    }

}
