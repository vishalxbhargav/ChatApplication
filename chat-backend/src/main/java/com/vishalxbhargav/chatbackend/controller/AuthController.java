package com.vishalxbhargav.chatbackend.controller;

import com.vishalxbhargav.chatbackend.config.TokenProvider;
import com.vishalxbhargav.chatbackend.exception.UserException;
import com.vishalxbhargav.chatbackend.model.User;
import com.vishalxbhargav.chatbackend.repository.UserRepository;
import com.vishalxbhargav.chatbackend.request.LoginRequest;
import com.vishalxbhargav.chatbackend.response.AuthResponse;
import com.vishalxbhargav.chatbackend.service.imp.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  TokenProvider tokenProvider;
    @Autowired
    private  CustomUserService customUserService;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
        User isUser=userRepository.findByEmail(user.getEmail());
        if(isUser!=null) throw new UserException("Alredy User exist with this email "+user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        String email=user.getEmail();String password=user.getPassword();
        var authentication=new UsernamePasswordAuthenticationToken(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=tokenProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse(jwt,true);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest log){
        var authentication=authenticate(log);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt=tokenProvider.generateToken(authentication);
        AuthResponse response=new AuthResponse(jwt,true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    public UsernamePasswordAuthenticationToken authenticate(LoginRequest log){
        UserDetails userDetails=customUserService.loadUserByUsername(log.getEmail());
        if(userDetails==null)
            throw new BadCredentialsException("Invailid Email....");
        if(!passwordEncoder.matches(log.getPassword(),userDetails.getPassword()))
            throw new BadCredentialsException("Invailid Passwod...");
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
