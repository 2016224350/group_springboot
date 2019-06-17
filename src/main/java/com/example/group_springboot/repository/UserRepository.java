package com.example.group_springboot.repository;

import com.example.group_springboot.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CustomizedRepoistory<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.number=:number")
    User find(@Param("number") String number);
    //查询最少监考的用户群
    @Query("select u from User u where u.teacher_test = (select min(teacher_test) from User)")
    List<User> findMin();
    @Query("SELECT u FROM User u")
    List<User> list();
}