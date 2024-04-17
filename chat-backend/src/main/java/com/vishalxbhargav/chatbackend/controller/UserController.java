package com.vishalxbhargav.chatbackend.controller;

import com.vishalxbhargav.chatbackend.exception.UserException;
import com.vishalxbhargav.chatbackend.model.User;
import com.vishalxbhargav.chatbackend.request.UpdateUserRequest;
import com.vishalxbhargav.chatbackend.response.ApiResponse;
import com.vishalxbhargav.chatbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private  UserService userService;
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException {
        User user=userService.findUserProfile(token);
        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }
    @GetMapping("/{query}")
    public ResponseEntity<List<User>> searchUserHandler(@PathVariable("query") String query ){
        List<User> users =userService.searchUser(query);
        return new ResponseEntity<List<User>>(users,HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateUserHandler(@RequestBody UpdateUserRequest req,@RequestHeader("Authorization") String token) throws UserException {
        User user=userService.findUserProfile(token);
        userService.updateUser(user.getId(),req);
        ApiResponse response=new ApiResponse("user updated Successfully",true);
        return new ResponseEntity<ApiResponse>(response,HttpStatus.OK);
    }
}
