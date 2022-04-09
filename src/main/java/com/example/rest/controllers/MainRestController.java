package com.example.rest.controllers;

import com.example.rest.dto.UserDTO;
import com.example.rest.models.User;
import com.example.rest.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
    public ResponseEntity<List<UserDTO>> getListUsers() {
        List<UserDTO> usersDTOList = new ArrayList<>(userService.getAllUsers()
                .stream()
                .map(a -> new UserDTO(a))
                .collect(Collectors.toList()));
        return usersDTOList.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(usersDTOList, HttpStatus.OK);
    }


    @GetMapping("/admin/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
        User user = userService.getById(id);
        UserDTO userDTO = new UserDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }


    @PostMapping("/admin")
    public ResponseEntity<UserDTO> newUser(@RequestBody UserDTO user) {
        User userSave = userService.save(new User(user));
        UserDTO userDTO = new UserDTO(userSave);
        final URI location = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("api/admin/{id}")
                .build()
                .expand(userDTO.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(userDTO, headers, HttpStatus.CREATED);
    }


    @PutMapping("/admin")
    public ResponseEntity<UserDTO> editUser(@RequestBody UserDTO user) {
        User userEdit = userService.edit(new User(user));
        UserDTO userDTO = new UserDTO(userEdit);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        User user = userService.getById(id);
        UserDTO userDTO = new UserDTO(user);

        if (userDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Юзер с id " + id + " не найден");
        }
        userService.delete(new User(userDTO));
        return new ResponseEntity<>("Юзер  был удален id =  " + id, HttpStatus.OK);
    }

}
