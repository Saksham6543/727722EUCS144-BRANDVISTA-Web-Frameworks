package com.example.brandvista.usercredentials.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.brandvista.usercredentials.model.User;
import com.example.brandvista.usercredentials.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User findById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
}
