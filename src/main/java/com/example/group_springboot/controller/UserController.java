package com.example.group_springboot.controller;

import com.example.group_springboot.entity.Invigilator;
import com.example.group_springboot.entity.InvigilatorDetail;
import com.example.group_springboot.entity.User;
import com.example.group_springboot.service.InvigilatorService;
import com.example.group_springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private InvigilatorService invigilatorService;

    @GetMapping("/getOnesAll/{tid}")
    public Map getOnesAll(@PathVariable int tid) {
        List<InvigilatorDetail> invigilatorDetails = invigilatorService.listTeacherInvigilator(tid);
        return Map.of("getOnesAll:", invigilatorDetails);
    }

    @PostMapping("/deliberate")
    public Map deliberate(@RequestBody Invigilator invigilator) {
        List<User> users = userService.findMinInvigilator();
        users.forEach(u -> {
            log.debug(u.getName());
        });
        InvigilatorDetail invigilatorDetail = new InvigilatorDetail();
        User teacher = new User();
        teacher.setId(users.get(0).getId());
        int t = users.get(0).getTeacher_test();
        users.get(0).setTeacher_test(t + 1);
        invigilatorDetail.setTeacher(teacher);
        invigilatorDetail.setInvigilator(invigilator);
        invigilatorService.addInvigilatorDetail(invigilatorDetail);
        return Map.of("deliberate:", invigilatorDetail);
    }
}