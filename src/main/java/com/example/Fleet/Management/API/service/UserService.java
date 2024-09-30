package com.example.Fleet.Management.API.service;

import com.example.Fleet.Management.API.model.User;
import com.example.Fleet.Management.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Creamos el metodo para crear un usuario
    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Page<User> getUsers(int page, int limit) {

        PageRequest pageable = PageRequest.of(page-1, limit);

        return userRepository.findAll(pageable);
    }
}
