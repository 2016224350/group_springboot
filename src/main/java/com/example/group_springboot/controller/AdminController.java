package com.example.group_springboot.controller;

import com.example.group_springboot.entity.Invigilator;
import com.example.group_springboot.entity.InvigilatorDetail;
import com.example.group_springboot.entity.User;
import com.example.group_springboot.repository.InvigilatorRepository;
import com.example.group_springboot.service.InvigilatorService;
import com.example.group_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private InvigilatorRepository invigilatorRepository;
    @Autowired
    private InvigilatorService invigilatorService;
    @PostMapping("/addteacher")
    public void postAddTeacher(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
    }

    //增加用户
    @PostMapping("/addUser")
    public Map addUser(@RequestBody User user){
        userService.addUser(user);
        return Map.of("newuser", user);
    }
    //增加监考科目
    @PostMapping("/addInvigilator")
    public Map addInvigilate(@RequestBody Invigilator invigilate){
        invigilatorRepository.save(invigilate);
        return Map.of("newInvigilator", invigilate);
    }
    //增加监考明细
    @PostMapping("/addInvigilatorDetail")
    public Map addInvigilateDetail(@RequestBody InvigilatorDetail invigilatorDetail){
        invigilatorService.addInvigilatorDetail(invigilatorDetail);
        return Map.of("newInvigilatorDetail", invigilatorDetail);
    }
}
