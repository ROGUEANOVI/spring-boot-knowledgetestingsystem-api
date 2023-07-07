package com.rogueanovi.knowledgetestingsystem.controller;

import com.rogueanovi.knowledgetestingsystem.model.auth.User;
import com.rogueanovi.knowledgetestingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public List<User> getUsersList(){
        return userService.getUsersList();
    }

    @GetMapping("/id/{id}")
    public User getUserById(@PathVariable("id") Long userId){
        return userService.getUserById(userId);
    }
    @GetMapping("username/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
    }

}
