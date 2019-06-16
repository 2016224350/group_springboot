package com.example.group_springboot.component;

import com.example.group_springboot.entity.InvigilatorDetail;
import com.example.group_springboot.service.InvigilatorDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Slf4j
@Component
public class MyTimer {
        @Autowired
        private InvigilatorDetailService invigilatorDetailService;

        @Scheduled(cron = "0 0 * * * *")
        public void deliverMessage(){
            List<InvigilatorDetail> invigilatorDetails = invigilatorDetailService.getAllInvigilatorDetails();
            LocalDateTime time = LocalDateTime.now();
            invigilatorDetails.forEach(i -> {
                if (i.getStartTime().isAfter(time)){
                    log.debug(i.getTeacher().getName());
                }
                invigilatorDetailService.updateInvigilatorDetail(i);
            });

        }
}
