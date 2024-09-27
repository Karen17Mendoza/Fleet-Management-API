package com.example.Fleet.Management.API.service;

import com.example.Fleet.Management.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Creamos el metodo para crear un usuario
    public User createUser( User user) {
        return userRepository.save(user);
    }
}
