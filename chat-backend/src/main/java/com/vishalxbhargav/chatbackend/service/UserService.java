package com.vishalxbhargav.chatbackend.service;
import com.vishalxbhargav.chatbackend.exception.UserException;
import com.vishalxbhargav.chatbackend.request.UpdateUserRequest;
import com.vishalxbhargav.chatbackend.model.User;

import java.util.List;

public interface UserService {
    User findUserById(Integer userId) throws UserException;
    User findUserProfile(String jwt) throws UserException;
    User updateUser(Integer userId,UpdateUserRequest req) throws UserException;
    List<User> searchUser(String query);
}
