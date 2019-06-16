package com.example.group_springboot.repository;

import com.example.group_springboot.entity.Invigilator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvigilatorRepository extends CustomizedRepoistory<Invigilator,Integer> {
    //指定监考id查询监考信息
    @Query("SELECT i FROM Invigilator i WHERE i.id=:iid")
    Invigilator find(@Param("iid") int iid);

    //查询所有监考信息
    @Query("SELECT i FROM Invigilator i")
    List<Invigilator> listAll();
}
