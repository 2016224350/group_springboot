package com.example.group_springboot.controller;

import com.example.group_springboot.entity.Invigilator;
import com.example.group_springboot.entity.InvigilatorDetail;
import com.example.group_springboot.entity.Task;
import com.example.group_springboot.entity.User;
import com.example.group_springboot.repository.InvigilatorRepository;
import com.example.group_springboot.service.InvigilatorService;
import com.example.group_springboot.service.TaskService;
import com.example.group_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private InvigilatorRepository invigilatorRepository;
    @Autowired
    private InvigilatorService invigilatorService;
    private PasswordEncoder passwordEncoder;
    //添加用户
    @PostMapping("/teachers")
    public Map postTeacher(@RequestBody User user){
        return Map.of("teacher", userService.saveUser(user));
    }
    //修改指定用户信息
    @PostMapping("/teachers/{tid}")
    public Map postTeacher(@RequestBody User user, @PathVariable int tid, @RequestAttribute int aid){
        return Map.of("teacher",userService.modifyUserDetail(user,tid,aid));
    }
    //添加普通用户为管理员
    @GetMapping("/teachers/{tid}/up")
    public Map putAdmin(@PathVariable int tid){
        return Map.of("teacher",userService.up(tid));
    }
    //删除管理员
    @GetMapping("/teachers/{tid}/down")
    public Map putTeacher(@PathVariable int tid){
        return Map.of("teacher",userService.down(tid));
    }

    //创建任务
    @PostMapping("/tasks")
    public Map postTask(@RequestBody Task task){
        return Map.of("task", taskService.saveTask(task));
    }
    //修改指定任务的信息
    @PostMapping("/tasks/{kid}")
    public Map postTask(@RequestBody Task task, @PathVariable int kid){
        return Map.of("task", taskService.modifyTask(task,kid));
    }
    //关闭指定的任务
    @GetMapping("/tasks/{kid}")
    public Map putTask(@PathVariable int kid) {
        return Map.of("task", taskService.closeTask(kid));

    }

    //增加监考科目
    @PostMapping("/addInvigilator")
    public Map addInvigilator(@RequestBody Invigilator invigilator){
        invigilatorRepository.save(invigilator);
        return Map.of("newInvigilator", invigilator);
    }
    //增加监考明细
    @PostMapping("/addInvigilatorDetail")
    public Map addInvigilatorDetail(@RequestBody InvigilatorDetail invigilatorDetail){
        invigilatorService.addInvigilatorDetail(invigilatorDetail);
        return Map.of("newInvigilatorDetail", invigilatorDetail);
    }
}
