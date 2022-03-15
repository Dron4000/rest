package com.example.rest.Services;


import com.example.rest.Models.User;
import com.example.rest.Repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    UserRepositories userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public UserServices(UserRepositories userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    public void save(User user) {
        if (user.getId() != null) {
            if (user.getPassword().equals(getById(user.getId()).getPassword())) {
                userRepository.save(user);
            }
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        userRepository.saveAndFlush(user);
    }

    public void edit(User user) {

        if (!user.getPassword().equals(getById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }


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

}
