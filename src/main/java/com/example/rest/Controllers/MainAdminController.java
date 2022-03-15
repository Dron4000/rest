package com.example.rest.Controllers;

import com.example.rest.Models.Role;
import com.example.rest.Models.User;
import com.example.rest.Services.RoleServices;
import com.example.rest.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;



@Controller
public class MainAdminController {


    private final UserServices userService;
    private final RoleServices roleService;

    @Autowired
    public MainAdminController(UserServices userService, RoleServices roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    String index() {
        return "login";
    }


    @GetMapping("/admin")
    public String listUser(ModelMap modelMap, @AuthenticationPrincipal User user) {
        modelMap.addAttribute("list", userService.getAllUsers());
        modelMap.addAttribute("roles", roleService.getAllRoles());
        modelMap.addAttribute("user", user);
        return "adminPage";
    }



    @GetMapping("/admin/user")
    public String infoUser(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "adminUserPage";
    }









}
