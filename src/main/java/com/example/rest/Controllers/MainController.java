package com.example.rest.Controllers;

import com.example.rest.Models.Role;
import com.example.rest.Models.User;
import com.example.rest.Services.RoleServices;
import com.example.rest.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(name = "/")
public class MainController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserServices userService;
    private final RoleServices roleService;

    @Autowired
    public MainController(UserServices userService, RoleServices roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    String index() {
        return "login";
    }


    @GetMapping("/admin")
    public List<User>listUser(ModelMap modelMap, @AuthenticationPrincipal User user) {

        //        modelMap.addAttribute("list",allUsers);
//        modelMap.addAttribute("roles", roleService.getAllRoles());
//        modelMap.addAttribute("user", user);
        return userService.getAllUsers();
    }


    @GetMapping("/admin/user")
    public String infoUser(@AuthenticationPrincipal User user, ModelMap model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "adminUserPage";
    }


    @PostMapping(value = "/admin/new")
    public String newUser(@ModelAttribute User user,
                          @RequestParam(value = "roless") Set<String> roles) {
        Set<Role> setRoles = roleService.getSetRoles(roles);
        user.setRoles(setRoles);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));


        userService.save(user);
        return "redirect:/admin";
    }


    @PostMapping(value = "/admin/edit/{id}")
    public String editUser(@ModelAttribute User user,
                           @RequestParam(value = "roless") Set<String> roles) {

        user.setRoles(roleService.getSetRoles(roles));


        if (user.getPassword().hashCode() != (userService.getById(user.getId()).getPassword().hashCode())) { // можно так
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        userService.save(user);

        return "redirect:/admin";
    }


    @PostMapping(value = "/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/admin";
    }
}
