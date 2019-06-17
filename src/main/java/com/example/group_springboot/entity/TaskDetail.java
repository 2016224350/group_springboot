package com.example.group_springboot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TaskDetail {
    public static final int ON_TIME = 1;
    public static final int NOT_NO_TIME = 2;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Task task;
    @ManyToOne
    private User user;
    @Column(columnDefinition = "TEXT")
    private String reply;
    //任务完成结果
    private int result;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
            updatable = false, insertable = false)
    private LocalDateTime completeTime;
}
