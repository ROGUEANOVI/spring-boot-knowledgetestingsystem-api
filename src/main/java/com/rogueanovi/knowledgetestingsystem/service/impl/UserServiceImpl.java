package com.rogueanovi.knowledgetestingsystem.service.impl;

import com.rogueanovi.knowledgetestingsystem.model.auth.User;
import com.rogueanovi.knowledgetestingsystem.model.auth.UserRole;
import com.rogueanovi.knowledgetestingsystem.repository.RoleRepository;
import com.rogueanovi.knowledgetestingsystem.repository.UserRepository;
import com.rogueanovi.knowledgetestingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<User> getUsersList(){
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @Override
    public User registerUser(User user, Set<UserRole> userRoles)  {

        for (UserRole userRole:userRoles){
            roleRepository.save(userRole.getRole());
        }
        user.getUserRoles().addAll(userRoles);

        return userRepository.save(user);
    }

    @Override
    public Boolean ValidateUserExists(String username, String email) {

        boolean userExistsWithUsername = userRepository.existsByUsername(username);
        boolean userExistsWithEmail = userRepository.existsByEmail(email);
        return userExistsWithUsername || userExistsWithEmail;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
