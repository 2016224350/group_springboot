package com.example.group_springboot.repository;

import com.example.group_springboot.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends CustomizedRepoistory<Task, Integer> {
    @Query("SELECT t FROM Task t WHERE t.status=1")
    List<Task> list();
    @Query("SELECT t FROM Task t")
    List<Task> listAll();
    @Query("SELECT t FROM Task t WHERE t.status=1 AND NOT EXISTS" +
            "(SELECT td FROM TaskDetail td WHERE td.user.id=:uid AND t.id=td.task.id)")
    List<Task> listByUser(@Param("uid") int uid);

}
