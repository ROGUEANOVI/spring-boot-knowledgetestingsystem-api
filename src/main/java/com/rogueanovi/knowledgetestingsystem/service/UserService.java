package com.rogueanovi.knowledgetestingsystem.service;

import com.rogueanovi.knowledgetestingsystem.model.auth.User;
import com.rogueanovi.knowledgetestingsystem.model.auth.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getUsersList();
    User getUserById(Long userId);
    User getUserByUsername(String username);
    Boolean ValidateUserExists(String username, String email);
    User registerUser(User user, Set<UserRole> userRoles);
    void deleteUser(Long userId);
}
