package com.example.rest.services;

import com.example.rest.models.Role;
import com.example.rest.repositories.RoleRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServices {

    @Autowired
    private RoleRepositories roleRepositories;

    @Transactional
    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepositories.findAll());
    }

    @Transactional
    public Set<Role> getSetRoles(Set<String> roles) {
        return roleRepositories.getSetRoles(roles);
    }
}
