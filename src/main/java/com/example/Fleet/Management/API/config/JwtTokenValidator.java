package com.example.Fleet.Management.API.config;

import com.example.Fleet.Management.API.exceptions.UnauthorizedException;
import com.example.Fleet.Management.API.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.SignatureException;

@Component
public class JwtTokenValidator {

    private final JwtService jwtService;

    @Autowired
    public JwtTokenValidator(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            return jwtService.isTokenValid(token, userDetails);
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException("Token has expired", e);
        } catch (MalformedJwtException e) {
            throw new UnauthorizedException("Malformed token", e);
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid token", e);
        }
    }
}