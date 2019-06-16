package com.example.group_springboot.repository;

import com.example.group_springboot.entity.TaskDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskDetailRepository extends CustomizedRepoistory<TaskDetail, Integer> {
    @Query("SELECT td FROM TaskDetail td WHERE td.task.id =:kid")
    List<TaskDetail> list(@Param("kid") int kid);
}
