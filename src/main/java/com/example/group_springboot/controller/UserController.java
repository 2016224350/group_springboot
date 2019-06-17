package com.example.group_springboot.controller;

import com.example.group_springboot.entity.Invigilator;
import com.example.group_springboot.entity.InvigilatorDetail;
import com.example.group_springboot.entity.User;
import com.example.group_springboot.service.InvigilatorService;
import com.example.group_springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.group_springboot.entity.Task;
import com.example.group_springboot.entity.TaskDetail;
import com.example.group_springboot.entity.User;
import com.example.group_springboot.service.TaskService;
import com.example.group_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    @Autowired
    private TaskService taskService;

    @GetMapping("/main")
    public Map getMain(@RequestAttribute int uid) {
        //加载主页面时，显示尚未完成的任务
        List<Task> tasks = null;
        tasks = taskService.listByUser(uid);
        return Map.of("tasks", tasks);
    }

    //修改个人信息
    @PostMapping("/teacher/detail")
    public Map postTeacher(@RequestBody User user,@RequestAttribute int uid,@RequestAttribute int aid){
        return Map.of("teacher",userService.modifyUserDetail(user,uid,aid));
    }
    //修改密码
    @PostMapping("/teacher/password")
    public Map postTeacher(@RequestBody Map<String,String> p, @RequestAttribute int uid){
        return Map.of("teacher",userService.modifyUserPassword(p.get("password"), uid));
    }

    //查看所有的任务（当前的/历史的）
    @GetMapping("/tasks")
    public Map getTasks(){
        List<Task> t = taskService.listAllTasks();
        Optional.ofNullable(t)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "尚无任务"));
        return Map.of("tasks", t);
    }
    //查看指定任务的其他所有用户的完成信息
    @GetMapping("/tasks/{kid}/taskDetails")
    public Map getTasks(@PathVariable int kid){
        List<TaskDetail> tds = taskService.listTaskDetails(kid);
        Optional.ofNullable(tds)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "尚无人回复"));
        return Map.of("taskDetails", tds);
    }
    //完成任务
    @PostMapping("/tasks/{kid}/reply")
    public Map postTask(@RequestBody Map<String,String> rep,@PathVariable int kid,@RequestAttribute int uid){
        return Map.of("task", taskService.saveTaskDetail(rep.get("reply"),kid,uid));
    }

    @GetMapping("/getOnesAll/{tid}")
    public Map getOnesAll(@PathVariable int tid) {
        List<InvigilatorDetail> invigilatorDetails = invigilatorService.listTeacherInvigilator(tid);
        return Map.of("getOnesAll:", invigilatorDetails);
    }

    @PostMapping("/deliberate")
    public Map deliberate(@RequestBody Invigilator invigilator) {
        List<User> users = userService.findMinInvigilator();
        users.forEach(u ->{
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