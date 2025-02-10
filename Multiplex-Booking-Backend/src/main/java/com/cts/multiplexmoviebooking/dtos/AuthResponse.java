package com.cts.multiplexmoviebooking.dtos;

import com.cts.multiplexmoviebooking.models.User;

public class AuthResponse extends User {
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
