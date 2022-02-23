package com.example.rest.Services;


import com.example.rest.Models.User;

import com.example.rest.Repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class UserServices implements UserDetailsService {


    @Autowired
    UserRepositories userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }


    public User getById(long id) {
        return userRepository.getById(id);
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
            return new User(user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getAge(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles());
        }

}
