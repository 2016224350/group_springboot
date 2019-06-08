package com.example.group_springboot.service;

import com.example.group_springboot.entity.User;
import com.example.group_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User getUser(String number){
        return userRepository.find(number);
    }
    public void addUser(User user){
        userRepository.save(user);
    }



}
