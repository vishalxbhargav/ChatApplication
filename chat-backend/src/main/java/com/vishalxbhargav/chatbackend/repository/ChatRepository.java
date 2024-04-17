package com.vishalxbhargav.chatbackend.repository;

import com.vishalxbhargav.chatbackend.model.Chat;
import com.vishalxbhargav.chatbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Integer> {
    @Query("select c from Chat c  join c.users u where u.id=:userId")
    List<Chat> findAllChatByUserId(@Param("userId")Integer userId);
    @Query("select c from Chat c where c.isGroup=false And :user Member of c.users And :reqUser Member of c.users")
    Chat findSingleChatByUserIds(@Param("user")User user,@Param("reqUser") User reqUser);

}
