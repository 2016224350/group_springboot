package com.example.group_springboot.service;

import com.example.group_springboot.entity.InvigilatorDetail;
import com.example.group_springboot.repository.InvigilatorDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InvigilatorDetailService {
    @Autowired
    private InvigilatorDetailRepository invigilatorDetailRepository;

    public InvigilatorDetail getAccurateInvigilator(int tid, int iid) {
        return invigilatorDetailRepository.find(iid, tid);
    }

    public List<InvigilatorDetail> getAllInvigilatorDetails() {
        return invigilatorDetailRepository.listAll();
    }

    public InvigilatorDetail updateInvigilatorDetail(InvigilatorDetail invigilateDetail) {
        invigilatorDetailRepository.save(invigilateDetail);
        return invigilateDetail;
    }
}
