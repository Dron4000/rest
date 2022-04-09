package com.example.rest.services;


import com.example.rest.models.User;
import com.example.rest.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class UserServices implements UserDetailsService {

    @Autowired
    private UserRepositories userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServices(UserRepositories userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    @Transactional
    public User save(User user) {
        if (user.getId() != null) {
            if (user.getPassword().equals(getById(user.getId()).getPassword())) {
                userRepository.save(user);
            }
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return user;
        }
        userRepository.saveAndFlush(user);
        return user;
    }

    @Transactional
    public User edit(User user) {

        if (!user.getPassword().equals(getById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        save(user);
        return user;
    }

    @Transactional
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public User getById(long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Юзер с id " + id + " не найден");
        }
        return user;
    }

    public User getUserByEmail(String email_address) {
        return userRepository.getUserByEmail(email_address);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email + " не найден");
        }
        return user;
    }

    @Transactional(readOnly = true)
    public User getAuthenticationUser(@AuthenticationPrincipal User user) {
        return user;

    }

}
