package com.example.group_springboot.service;

import com.example.group_springboot.entity.Invigilator;
import com.example.group_springboot.entity.InvigilatorDetail;
import com.example.group_springboot.repository.InvigilatorDetailRepository;
import com.example.group_springboot.repository.InvigilatorRepository;
import com.example.group_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvigilatorService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InvigilatorRepository invigilatorRepository;
    @Autowired
    private InvigilatorDetailRepository invigilatorDetailRepository;

    //获取所有的监考
    public List<Invigilator> listAllInvigilator(){
        return invigilatorRepository.listAll();
    }

    //获取指定教师的所有监考信息
    public List<InvigilatorDetail> listTeacherInvigilator(int tid){
        return invigilatorDetailRepository.listByTeacherId(tid);
    }

    //添加监考
    public Invigilator addInvigilator(Invigilator invigilator){
        invigilatorRepository.save(invigilator);
        return invigilatorRepository.refresh(invigilator);
    }

    //添加监考的详细信息
    public InvigilatorDetail addInvigilatorDetail(InvigilatorDetail invigilatorDetail){
        invigilatorDetailRepository.save(invigilatorDetail);
        return invigilatorDetailRepository.refresh(invigilatorDetail);
    }

    //获取指定id的监考信息
    public Invigilator findInvigilatorById(int iid){
        return invigilatorRepository.find(iid);
    }
}
