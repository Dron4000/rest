package com.example.rest.Controllers;

import com.example.rest.DTO.UserDTO;
import com.example.rest.Models.User;
import com.example.rest.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainRestController {


    @Autowired
    private final UserServices userService;


    @Autowired
    public MainRestController(UserServices userService) {
        this.userService = userService;

    }


    @GetMapping("/admin")
    public ResponseEntity<List<User>> getListUsers() {
        List<User> allUsers = userService.getAllUsers();
        return !allUsers.isEmpty()
                ? new ResponseEntity<>(allUsers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/admin/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }


    @PostMapping(value = "/admin")
    public ResponseEntity<User> newUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PutMapping(value = "/admin")
    public ResponseEntity<User> editUser(@RequestBody User user) {

        userService.edit(user);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @DeleteMapping(value = "/admin/{id}")
    public String deleteUser(@PathVariable long id) {

        UserDTO user = UserDTO.fromUser(userService.getById(id));
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Юзер с id " + id + " не найден");
        }

        userService.delete(user.toUser());
        return "Юзер с id " + id + " был удален и  статсус ответа - " + HttpStatus.OK;
    }

}
