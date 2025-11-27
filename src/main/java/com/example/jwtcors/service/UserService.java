package com.example.jwtcors.service;

import com.example.jwtcors.entity.Role;
import com.example.jwtcors.entity.User;
import com.example.jwtcors.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerNewUser(String username, String password, Role role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole(role);
        return  userRepository.save(user);
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public boolean checkPassword(String raw, String encoded) {
        return encoder.matches(raw, encoded);
    }
}
