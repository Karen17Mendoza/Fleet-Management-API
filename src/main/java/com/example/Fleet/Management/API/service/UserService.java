package com.example.Fleet.Management.API.service;

import com.example.Fleet.Management.API.model.User;
import com.example.Fleet.Management.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Metodo para crear a un usuario
    public User createUser(User user) {

        return userRepository.save(user);
    }

    //Metodo para listar los usuarios creados
    public Page<User> getUsers(int page, int limit) {

        PageRequest pageable = PageRequest.of(page-1, limit);

        return userRepository.findAll(pageable);
    }

    //Metodo para actualiar un usuario
    public Optional<User> updateUser(String uid, User newUserInfo) {
        //Buscamos por id o email al usuario existente
        // Verificamos si el UID es un número (ID) o un email
        Optional<User> userOptional;
        try {
            Integer id = Integer.parseInt(uid);
            userOptional = userRepository.findById(id);
        } catch (NumberFormatException e) {
            // Si no es un número, lo tratamos como un email
            userOptional = userRepository.findByEmail(uid);
        }

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(newUserInfo.getName());
            user.setEmail(newUserInfo.getEmail());

            // Aquí puedes agregar lógica para manejar otras actualizaciones, pero no incluyas el password si no es necesario
            return Optional.of(userRepository.save(user));
        } else {
            return Optional.empty();
        }
    }

}
