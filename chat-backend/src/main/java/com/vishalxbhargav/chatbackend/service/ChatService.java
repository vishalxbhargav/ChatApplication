package com.vishalxbhargav.chatbackend.service;

import com.vishalxbhargav.chatbackend.exception.UserException;
import com.vishalxbhargav.chatbackend.exception.ChatException;
import com.vishalxbhargav.chatbackend.model.User;
import com.vishalxbhargav.chatbackend.request.GroupChatRequest;
import com.vishalxbhargav.chatbackend.model.Chat;

import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser, Integer user2) throws UserException;
    public Chat findChatById(Integer chatId) throws ChatException;
    public List<Chat> findAllChatByUserId(Integer userId) throws UserException;
    public Chat createGroup(GroupChatRequest req,User reqUser) throws UserException;
    public Chat addUserToGroup(Integer userId,Integer chatId,User reqUser) throws UserException,ChatException;
    public Chat renameGroup(Integer chatId,String groupName,User reqUser) throws UserException,ChatException;
    public Chat removeFromGroup(Integer chatId,Integer userId,User reqUser) throws UserException,ChatException;
    public void deleteChat(Integer chatId,Integer userId) throws UserException,ChatException;
}
