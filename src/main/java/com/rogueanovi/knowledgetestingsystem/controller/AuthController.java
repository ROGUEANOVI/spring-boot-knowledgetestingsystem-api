package com.rogueanovi.knowledgetestingsystem.controller;

import com.rogueanovi.knowledgetestingsystem.configuration.JwtUtil;
import com.rogueanovi.knowledgetestingsystem.dto.BaseResponseDto;
import com.rogueanovi.knowledgetestingsystem.dto.auth.SignupDto;
import com.rogueanovi.knowledgetestingsystem.model.auth.*;
import com.rogueanovi.knowledgetestingsystem.service.UserService;
import com.rogueanovi.knowledgetestingsystem.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;


@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;


    @PostMapping("register")
    public ResponseEntity<BaseResponseDto> register(@RequestBody SignupDto signupDto) {

        BaseResponseDto response = new BaseResponseDto();

        boolean userExists = userService.ValidateUserExists(signupDto.getUsername(), signupDto.getEmail());

        if (userExists) {
            response.setIsSuccessful(false);
            response.setMessage("Username or Email is already taken!");
            response.setData(null);
            response.setError("");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setFirstName(signupDto.getFirstName());
        user.setLastName(signupDto.getLastName());
        user.setPhoneNumber(signupDto.getPhoneNumber());
        user.setUsername(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        user.setProfile(signupDto.getProfile());

        Role role = new Role();
        role.setRoleId(2L);
        role.setRolename("USER");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        Set<UserRole> userRoles =  new HashSet<>();
        userRoles.add(userRole);

        try {
            userService.registerUser(user, userRoles);
            response.setIsSuccessful(true);
            response.setMessage("User registered successfully");
            response.setData(null);
            response.setError("");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            response.setIsSuccessful(false);
            response.setMessage("An error occurred while registering");
            response.setData(null);
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<BaseResponseDto> authenticate (@RequestBody JwtRequest jwtRequest) {
        BaseResponseDto response = new BaseResponseDto();

        try{
            this.authenticate(jwtRequest.getUsernameOrEmail(), jwtRequest.getPassword());
            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsernameOrEmail());
            String token = jwtUtil.generateToken(userDetails);
            response.setIsSuccessful(true);
            response.setMessage("user authenticated successfully");
            response.setData(new JwtResponse(token));
            response.setError(null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception  e){
            response.setIsSuccessful(false);
            response.setMessage(e.getCause().getMessage());
            response.setData(null);
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user-loggedin")
    public ResponseEntity<BaseResponseDto> getUserLoggedIn(Principal principal) {
        BaseResponseDto response = new BaseResponseDto();
        try {
            var user = this.userDetailsService.loadUserByUsername(principal.getName());
            response.setIsSuccessful(true);
            response.setMessage("user found successfully");
            response.setData(user);
            response.setError(null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            response.setIsSuccessful(false);
            response.setMessage(e.getCause().getMessage());
            response.setData(null);
            response.setError(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    private void authenticate(String usernameOrEmail, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usernameOrEmail, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED " + e.getMessage(), e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS " + e.getMessage(), e);
        }
    }
}
