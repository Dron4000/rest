package com.example.rest.dto;

import com.example.rest.models.Role;
import com.example.rest.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;
    private String FirstName;
    private String LastName;
    private int age;
    private String Email;
    private String password;
    private Set<Role> roles;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.FirstName = user.getFirstName();
        this.LastName = user.getLastName();
        this.age = user.getAge();
        this.Email  = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

//    public User toUser() {
//        User user = new User();
//        user.setId(id);
//        user.setFirstName(FirstName);
//        user.setLastName(LastName);
//        user.setAge(age);
//        user.setEmail(Email);
//        user.setPassword(password);
//        user.setRoles(roles);
//        return user;
//    }
//
//    public static UserDTO fromUser(User user) {
//        UserDTO userDTO = new UserDTO(user);
//
//        userDTO.setId(user.getId());
//        userDTO.setFirstName(user.getFirstName());
//        userDTO.setLastName(user.getLastName());
//        userDTO.setAge(user.getAge());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setRoles(user.getRoles());
//        return userDTO;
//
//
//    }


}
