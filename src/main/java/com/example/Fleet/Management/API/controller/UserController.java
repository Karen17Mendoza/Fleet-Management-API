package com.example.Fleet.Management.API.controller;

import com.example.Fleet.Management.API.errors.ErrorResponseHandler;
import com.example.Fleet.Management.API.model.User;
import com.example.Fleet.Management.API.response.UserResponse;
import com.example.Fleet.Management.API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Creamos el endpoint para crear el usuario
    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        //Verificamos si el email y el password esten presentes
        if ( user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {

            //Usamos el manejador de errores
            return ErrorResponseHandler.generateErrorResponse(
                    "string",
                    HttpStatus.BAD_REQUEST
            );
        }
        //Creamos el usuario
        User createdUser = userService.createUser(user);
        //Devolvemos la respuesta sin el pasword
        UserResponse userResponse = new UserResponse(
                createdUser.getId(),
                createdUser.getName(),
                createdUser.getEmail()
        );

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}
