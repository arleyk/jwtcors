package com.example.jwtcors.controller;

import com.example.jwtcors.entity.Role;
import com.example.jwtcors.entity.User;
import com.example.jwtcors.service.JwtUtil;
import com.example.jwtcors.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public User register(@RequestParam String username,@RequestParam String password,@RequestParam Role role){
        return userService.registerNewUser(username,password,role);
    }
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password){
        User u = userService.findByUsername(username);

        if(u == null || !userService.checkPassword(password, u.getPassword())){
            throw  new RuntimeException("Credenciales invalidas");
        }

        return jwtUtil.generateToken(u.getUsername(), u.getRole());
    }

}
