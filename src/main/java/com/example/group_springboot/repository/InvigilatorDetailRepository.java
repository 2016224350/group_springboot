package com.example.group_springboot.repository;

import com.example.group_springboot.entity.InvigilatorDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvigilatorDetailRepository extends CustomizedRepoistory<InvigilatorDetail,Integer>{
    //根据教室id查监考场次
    @Query("SELECT i FROM InvigilatorDetail i WHERE i.teacher.id=:tid")
    List<InvigilatorDetail> listByTeacherId(@Param("tid") int tid);

    //查询所有监考场次
    @Query("SELECT i FROM InvigilatorDetail i")
    List<InvigilatorDetail> listAll();

    @Query("SELECT i FROM InvigilatorDetail i WHERE i.invigilator.id=:iid AND i.teacher.id=:tid")
    InvigilatorDetail find(@Param("iid") int iid,@Param("tid") int tid);
}
