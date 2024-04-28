package com.vishalxbhargav.chatbackend.repository;

import com.vishalxbhargav.chatbackend.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Query("Select m from Message m join m.chat c where c.id=:chatId")
    public List<Message> findByChatId(@Param("chatId") Integer chatId);
}
