package com.rogueanovi.knowledgetestingsystem.dto.auth;

import lombok.Data;

@Data
public class SignupDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String email;
    private String password;
    private String Profile;
}
