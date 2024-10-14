package com.example.Fleet.Management.API.controller;

import com.example.Fleet.Management.API.errors.ErrorResponseHandler;
import com.example.Fleet.Management.API.model.User;
import com.example.Fleet.Management.API.response.UserResponse;
import com.example.Fleet.Management.API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //Creamos el endpoint para crear el usuario
    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {

            return ErrorResponseHandler.generateErrorResponse(
                    "Email and password are required",
                    HttpStatus.BAD_REQUEST
            );
        }
        User createdUser = userService.createUser(user);
        UserResponse userResponse = new UserResponse(
                createdUser.getId(),
                createdUser.getName(),
                createdUser.getEmail()
        );

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    //Creamos el endpoint para la obtencion de usuarios
    @GetMapping
    public ResponseEntity<Object> getUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {

        if (page < 1 || limit < 1) {
            return ErrorResponseHandler.generateErrorResponse(
                    "Invalid page or limit", HttpStatus.BAD_REQUEST);
        }

        Page<User> userPage = userService.getUsers(page, limit);
        if (userPage.hasContent()) {
            // Convertimos los usuarios a UserResponse
            List<UserResponse> userResponses = userPage.getContent()
                    .stream()
                    .map(user -> new UserResponse(
                            user.getId(),
                            user.getName(),
                            user.getEmail()))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(userResponses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    //Creamos el endpoint para actualizar un usuario existente
    @PatchMapping ("/{uid}")
    public ResponseEntity<Object> updateUser(@PathVariable String uid, @RequestBody User newUserInfo) {
        // Llamamos al servicio para actualizar el usuario
        Optional<User> updatedUser = userService.updateUser(uid, newUserInfo);

        if (updatedUser.isPresent()) {
            User user = updatedUser.get();
            UserResponse userResponse = new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail());
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            return ErrorResponseHandler.generateErrorResponse(
                    "User not found", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{uid}")
    public ResponseEntity<Object> deleteUser(@PathVariable String uid) {
        Optional<User> deletedUser = userService.deleteUser(uid);

        if (deletedUser.isPresent()) {
            User user = deletedUser.get(); // Obtenemos el usuario eliminado
            UserResponse userResponse = new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail()
            );
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } else {
            return ErrorResponseHandler.generateErrorResponse(
                    "User not found", HttpStatus.NOT_FOUND);
        }
    }

}
