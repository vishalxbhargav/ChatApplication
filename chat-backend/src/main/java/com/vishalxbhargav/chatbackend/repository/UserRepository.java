package com.vishalxbhargav.chatbackend.repository;

import com.vishalxbhargav.chatbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    @Query("select u from user u where u.full_name like %:query% or u.email like %:query%")
    List<User> searchUser(@Param("query")String query);
}
