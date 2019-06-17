package com.example.group_springboot.service;

import com.example.group_springboot.entity.User;
import com.example.group_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(String number){
        return userRepository.find(number);
    }
    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return userRepository.refresh(user);
    }
    public User modifyUserDetail(User user,int tid,int aid){
        User u = userRepository.findById(tid).get();
        u.setName(user.getName());u.setTeacher_detail(user.getTeacher_detail());
        u.setTeacher_phone(user.getTeacher_phone());
        if(aid == 2){
            u.setTeacher_title(user.getTeacher_title());
        }
        return userRepository.save(u);
    }
    public User modifyUserPassword(String password,int tid){
        User u = userRepository.findById(tid).get();
        u.setPassword(passwordEncoder.encode(password));
        return userRepository.save(u);
    }
    public User up(int tid){
        User u = userRepository.findById(tid).get();
        u.setAuthority(2);
        return userRepository.save(u);
    }
    public User down(int tid){
        User u = userRepository.findById(tid).get();
        u.setAuthority(1);
        return userRepository.save(u);
    }
    public List<User> listUser(){
        return userRepository.list();
    }
    public List<User> findMinInvigilator(){
        return userRepository.findMin();
    }
}
