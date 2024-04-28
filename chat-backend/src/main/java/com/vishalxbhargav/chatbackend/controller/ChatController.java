package com.vishalxbhargav.chatbackend.controller;

import com.vishalxbhargav.chatbackend.config.TokenProvider;
import com.vishalxbhargav.chatbackend.exception.ChatException;
import com.vishalxbhargav.chatbackend.exception.UserException;
import com.vishalxbhargav.chatbackend.model.Chat;
import com.vishalxbhargav.chatbackend.model.User;
import com.vishalxbhargav.chatbackend.request.GroupChatRequest;
import com.vishalxbhargav.chatbackend.request.SingleChatRequest;
import com.vishalxbhargav.chatbackend.response.ApiResponse;
import com.vishalxbhargav.chatbackend.service.ChatService;
import com.vishalxbhargav.chatbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenProvider tokenProvider;
    @PostMapping("/single")
    public ResponseEntity<Chat> createChatHandler(@RequestBody SingleChatRequest res, @RequestHeader("Authorization")String token) throws UserException {
        User reqUser=userService.findUserProfile(token);
        Chat chat=chatService.createChat(reqUser,res.getUserId());
        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }
    @PostMapping("/group")
    public ResponseEntity<Chat> creatGroupHandler(@RequestBody GroupChatRequest res, @RequestHeader("Authorization")String token) throws UserException {
        User reqUser=userService.findUserProfile(token);
        Chat chat=chatService.createGroup(res,reqUser);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> findChatByIdHandler(@PathVariable Integer chatId, @RequestHeader("Authorization")String token) throws UserException, ChatException {
        Chat chat=chatService.findChatById(chatId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<List<Chat>> createGroupHandler(@RequestHeader("Authorization")String token) throws UserException, ChatException {
        User reqUser=userService.findUserProfile(token);
        List<Chat> chats=chatService.findAllChatByUserId(reqUser.getId());
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }
    @PutMapping("/{chatId}/add/{userId}")
    public ResponseEntity<Chat> addUserToGroupHandler(@PathVariable Integer chatId,@PathVariable Integer userId,@RequestHeader("Authorization")String token) throws UserException, ChatException {
        User reqUser=userService.findUserProfile(token);
        Chat chat=chatService.addUserToGroup(userId,chatId,reqUser);
        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }
    @PutMapping("/{chatId}/remove/{userId}")
    public ResponseEntity<Chat> removeUserToGroupHandler(@PathVariable Integer chatId,@PathVariable Integer userId,@RequestHeader("Authorization")String token) throws UserException, ChatException {
        User reqUser=userService.findUserProfile(token);
        Chat chat=chatService.removeFromGroup(userId,chatId,reqUser);
        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{chatId}")
    public ResponseEntity<ApiResponse> deleteChatHandler(@PathVariable Integer chatId, @PathVariable Integer userId, @RequestHeader("Authorization")String token) throws UserException, ChatException {
        User reqUser=userService.findUserProfile(token);
        chatService.deleteChat(chatId,reqUser.getId());
        ApiResponse res=new ApiResponse("Chat is Deleted Successfully",false);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
