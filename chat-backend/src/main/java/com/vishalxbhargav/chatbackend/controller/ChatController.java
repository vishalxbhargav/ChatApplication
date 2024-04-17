package com.vishalxbhargav.chatbackend.controller;

import com.vishalxbhargav.chatbackend.config.TokenProvider;
import com.vishalxbhargav.chatbackend.exception.ChatException;
import com.vishalxbhargav.chatbackend.exception.UserException;
import com.vishalxbhargav.chatbackend.model.Chat;
import com.vishalxbhargav.chatbackend.model.User;
import com.vishalxbhargav.chatbackend.request.GroupChatRequest;
import com.vishalxbhargav.chatbackend.request.SingleChatRequest;
import com.vishalxbhargav.chatbackend.service.ChatService;
import com.vishalxbhargav.chatbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenProvider tokenProvider;
    @PostMapping()
    public ResponseEntity<Chat> createChatHandler(@RequestBody SingleChatRequest res, @RequestHeader("Authorization")String token) throws UserException {
        User reqUser=userService.findUserProfile(token);
        Chat chat=chatService.createChat(reqUser,res.getUserId());
        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }
    @PostMapping("/group")
    public ResponseEntity<Chat> creatGroupHandler(@RequestBody GroupChatRequest res, @RequestHeader("Authorization")String token) throws UserException {
        User reqUser=userService.findUserProfile(token);
        Chat chat=chatService.createGroup(res,reqUser);
        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }
    @PostMapping("/{chatId}")
    public ResponseEntity<Chat> findChatByIdHandler(@PathVariable Integer chatId, @RequestHeader("Authorization")String token) throws UserException, ChatException {
        Chat chat=chatService.findChatById(chatId);
        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }
}
