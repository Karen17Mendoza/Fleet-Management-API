package com.example.Fleet.Management.API.controller;

import com.example.Fleet.Management.API.response.LoginResponse;
import com.example.Fleet.Management.API.response.UserResponse;
import com.example.Fleet.Management.API.service.AuthenticationService;
import com.example.Fleet.Management.API.service.JwtService;
import com.example.Fleet.Management.API.dtos.LoginUserDto;
import com.example.Fleet.Management.API.dtos.RegisterUserDto;
import com.example.Fleet.Management.API.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        // Generar el token JWT
        String jwtToken = jwtService.generateToken(authenticatedUser);

        // Crear un objeto UserResponse
        UserResponse userResponse = new UserResponse(authenticatedUser.getId(), authenticatedUser.getName(), authenticatedUser.getEmail());

        // Crear la respuesta LoginResponse
        LoginResponse loginResponse = new LoginResponse()
                .setAccessToken(jwtToken)  // Usar accessToken en lugar de token
                .setUser(userResponse);    // Establecer la información del usuario

        return ResponseEntity.ok(loginResponse);
    }
}
