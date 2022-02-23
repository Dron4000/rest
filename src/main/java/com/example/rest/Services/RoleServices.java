package com.example.rest.Services;

import com.example.rest.Models.Role;
import com.example.rest.Repositories.RoleRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServices {

    @Autowired
    RoleRepositories roleRepositories;


    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepositories.findAll());
    }


    public Set<Role> getSetRoles(Set<String> roles) {
        return roleRepositories.getSetRoles(roles);
    }
}
