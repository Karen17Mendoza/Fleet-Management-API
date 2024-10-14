package com.example.Fleet.Management.API.response;

public class LoginResponse {
    private String accessToken;
    private UserResponse user;

    // Getter y setter para accessToken
    public String getAccessToken() {
        return accessToken;
    }

    public LoginResponse setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    // Getter y setter para user
    public UserResponse getUser() {
        return user;
    }

    public LoginResponse setUser(UserResponse user) {
        this.user = user;
        return this;
    }
}
