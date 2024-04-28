package com.vishalxbhargav.chatbackend.controller;

import com.vishalxbhargav.chatbackend.exception.ChatException;
import com.vishalxbhargav.chatbackend.exception.MessageException;
import com.vishalxbhargav.chatbackend.exception.UserException;
import com.vishalxbhargav.chatbackend.model.Message;
import com.vishalxbhargav.chatbackend.model.User;
import com.vishalxbhargav.chatbackend.request.SendMessageRequest;
import com.vishalxbhargav.chatbackend.response.ApiResponse;
import com.vishalxbhargav.chatbackend.service.MessageService;
import com.vishalxbhargav.chatbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public ResponseEntity<Message> sendMessageHandler(@RequestBody SendMessageRequest req, @RequestHeader("Authorization") String token) throws UserException, ChatException {
        User user=userService.findUserProfile(token);
        req.setUserId(user.getId());
        Message message=messageService.sendMessage(req);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> getChatMessagesHandler(@PathVariable Integer chatId, @RequestHeader("Authorization") String token) throws UserException, ChatException {
        User user=userService.findUserProfile(token);
        List<Message> messages=messageService.getChatMessages(chatId,user);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    @DeleteMapping("/{messageId}")
    public ResponseEntity<ApiResponse> deleteMessagesHandler(@PathVariable Integer messageId, @RequestHeader("Authorization") String token) throws UserException, ChatException, MessageException {
        User user=userService.findUserProfile(token);
        messageService.deleteMessage(messageId,user);
        ApiResponse res=new ApiResponse("message successfully deleted",false);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
