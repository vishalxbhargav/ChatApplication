package com.vishalxbhargav.chatbackend.service;

import com.vishalxbhargav.chatbackend.exception.UserException;
import com.vishalxbhargav.chatbackend.exception.ChatException;
import com.vishalxbhargav.chatbackend.request.GroupChatRequest;
import com.vishalxbhargav.chatbackend.model.Chat;

import java.util.List;

public interface ChatService {
    public Chat createChat(Integer user1,Integer user2) throws UserException;
    public Chat findChatById(Integer chatId) throws ChatException;
    public List<Chat> findAllChatByUserId(Integer userId) throws UserException;
    public Chat createGroup(GroupChatRequest req,Integer reqUserId) throws UserException;
    public Chat addUserToGroup(Integer userId,Integer chatId) throws UserException,ChatException;
    public Chat renameGroup(Integer chatId,String groupName,Integer reqUser) throws UserException,ChatException;
    public Chat removeFromGroup(Integer chatId,Integer userId,Integer reqUser) throws UserException,ChatException;
    public Chat deleteChat(Integer chatId,Integer userId) throws UserException,ChatException;
}
