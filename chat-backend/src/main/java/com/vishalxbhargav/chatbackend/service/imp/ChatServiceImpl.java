package com.vishalxbhargav.chatbackend.service.imp;

import com.vishalxbhargav.chatbackend.exception.ChatException;
import com.vishalxbhargav.chatbackend.exception.UserException;
import com.vishalxbhargav.chatbackend.model.Chat;
import com.vishalxbhargav.chatbackend.repository.ChatRepository;
import com.vishalxbhargav.chatbackend.request.GroupChatRequest;
import com.vishalxbhargav.chatbackend.service.ChatService;
import com.vishalxbhargav.chatbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserService userService;
    @Override
    public Chat createChat(Integer user1, Integer user2) throws UserException {
        return null;
    }

    @Override
    public Chat findChatById(Integer chatId) throws ChatException {
        return null;
    }

    @Override
    public List<Chat> findAllChatByUserId(Integer userId) throws UserException {
        return null;
    }

    @Override
    public Chat createGroup(GroupChatRequest req, Integer reqUserId) throws UserException {
        return null;
    }

    @Override
    public Chat addUserToGroup(Integer userId, Integer chatId) throws UserException, ChatException {
        return null;
    }

    @Override
    public Chat renameGroup(Integer chatId, String groupName, Integer reqUser) throws UserException, ChatException {
        return null;
    }

    @Override
    public Chat removeFromGroup(Integer chatId, Integer userId, Integer reqUser) throws UserException, ChatException {
        return null;
    }

    @Override
    public Chat deleteChat(Integer chatId, Integer userId) throws UserException, ChatException {
        return null;
    }
}
