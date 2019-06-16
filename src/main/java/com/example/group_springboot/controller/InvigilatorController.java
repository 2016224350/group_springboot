package com.example.group_springboot.controller;

import com.example.group_springboot.repository.InvigilatorRepository;
import com.example.group_springboot.service.InvigilatorDetailService;
import com.example.group_springboot.service.InvigilatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class InvigilatorController {
    @Autowired
    private InvigilatorService invigilatorService;
    @Autowired
    private InvigilatorRepository invigilatorRepository;
    @Autowired
    private InvigilatorDetailService invigilatorDetailService;

    //获取所有监考科目
    @GetMapping("/getAllInvigilator")
    public Map getAllInvigilate(){
        return Map.of("allInvigilator", invigilatorService.listAllInvigilator());
    }


    //根据教师和科目确定监考场次
    @GetMapping("/teacher/{tid}/invigilator/{iid}")
    public Map getAccurateInvigilate(@PathVariable int tid, @PathVariable int iid){
        return Map.of("invigilate:", invigilatorDetailService.getAccurateInvigilator(tid, iid));
    }
}
