package com.example.group_springboot.service;

import com.example.group_springboot.entity.Task;
import com.example.group_springboot.entity.TaskDetail;
import com.example.group_springboot.entity.User;
import com.example.group_springboot.repository.TaskDetailRepository;
import com.example.group_springboot.repository.TaskRepository;
import com.example.group_springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskDetailRepository taskDetailRepository;
    @Autowired
    private UserRepository userRepository;

    public Task saveTask(Task task){
        taskRepository.save(task);
        return taskRepository.refresh(task);
    }
    public Task modifyTask(Task task,int kid){
        Task t = taskRepository.findById(kid).get();
        if(t.getStatus()==0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "任务已关闭。");
        }else{
            t.setDeadLineTime(task.getDeadLineTime());
            t.setTitle(task.getTitle());
            t.setContent(task.getContent());
            return taskRepository.save(t);
        }
    }
    public Task closeTask(int kid){
        Task t = taskRepository.findById(kid).get();
        t.setStatus(0);
        return taskRepository.save(t);
    }
    public List<Task> listAllTasks(){
        return  taskRepository.listAll();
    }
    public List<Task> listByUser(int uid){
        return  taskRepository.listByUser(uid);
    }
    public List<TaskDetail> listTaskDetails(int kid){
        return taskDetailRepository.list(kid);
    }
    public TaskDetail saveTaskDetail(String reply,int kid,int uid){
        Task t = taskRepository.findById(kid).get();
        if(t.getStatus() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "任务已关闭。");
        }else{
            TaskDetail taskDetail = new TaskDetail();
            if(LocalDateTime.now().isBefore(t.getDeadLineTime())){
                taskDetail.setResult(1);
            }else{
                taskDetail.setResult(2);
            }
            taskDetail.setReply(reply);
            taskDetail.setTask(taskRepository.findById(kid).get());
            taskDetail.setUser(userRepository.findById(uid).get());
            return taskDetailRepository.save(taskDetail);
        }
    }


}
