package com.example.rest.DTO;

import com.example.rest.Models.Role;
import com.example.rest.Models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties
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

    public User toUser() {
        User user = new User();
        user.setId(id);
        user.setFirstName(FirstName);
        user.setLastName(LastName);
        user.setAge(age);
        user.setEmail(Email);
        user.setPassword(password);
        user.setRoles(roles);
        return user;
    }

    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setAge(user.getAge());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoles(user.getRoles());
        return userDTO;


    }


}
