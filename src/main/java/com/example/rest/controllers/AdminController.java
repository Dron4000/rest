package com.example.rest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {


    @GetMapping("/login")
    public String index() {
        return "login";
    }


    @GetMapping("/admin")
    public String listUser() {
        return "adminPage";
    }


    @GetMapping(path = {"/admin/user", "/admin/api/incomingUser"})
    public String infoUser() {
        return "adminUserPage";
    }


}
