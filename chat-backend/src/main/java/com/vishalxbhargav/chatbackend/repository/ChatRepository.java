package com.vishalxbhargav.chatbackend.repository;

import com.vishalxbhargav.chatbackend.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat,Integer> {

}
