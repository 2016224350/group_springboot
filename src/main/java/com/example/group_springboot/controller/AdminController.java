package com.example.group_springboot.controller;

import com.example.group_springboot.entity.User;
import com.example.group_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/addteacher")
    public void postAddTeacher(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
    }



}
