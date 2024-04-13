package com.vishalxbhargav.chatbackend.service.imp;

import com.vishalxbhargav.chatbackend.config.TokenProvider;
import com.vishalxbhargav.chatbackend.exception.UserException;
import com.vishalxbhargav.chatbackend.model.User;
import com.vishalxbhargav.chatbackend.repository.UserRepository;
import com.vishalxbhargav.chatbackend.request.UpdateUserRequest;
import com.vishalxbhargav.chatbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public final UserRepository userRepository;
    @Autowired
    public final TokenProvider tokenProvider;
    @Override
    public User findUserById(Integer userId) throws UserException {
        Optional<User> user= userRepository.findById(userId);
        if(user.isPresent()) return user.get();
        throw new UserException("User not found wit id "+userId);
    }

    @Override
    public User findUserProfile(String jwt) throws UserException {
        String email=tokenProvider.getEmailByToken(jwt);
        if(email==null) throw new BadCredentialsException("Invailid token...");
        User user= userRepository.findByEmail(email);
        if(user==null) throw new UserException("User not found with email "+email);
        return user;
    }

    @Override
    public User updateUser(Integer userId, UpdateUserRequest req) throws UserException {
        User user =findUserById(userId);
        if(req.getFull_name()!=null) user.setFull_name(req.getFull_name());
        if(req.getProfile_picture()!=null) user.setProfile_Picture(req.getProfile_picture());
        return userRepository.save(user);
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }
}
