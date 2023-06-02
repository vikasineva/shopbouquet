package com.ua.rosella.service;

import com.ua.rosella.model.User;
import com.ua.rosella.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUserEmail(String userEmail) {
        var user = userRepository.findUserByEmail(userEmail);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }
}
