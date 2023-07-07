package com.rogueanovi.knowledgetestingsystem.model.auth;

import lombok.Data;

@Data
public class JwtRequest {
    private String usernameOrEmail;
    private String password;

    public JwtRequest() {
    }

    public JwtRequest(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

}
