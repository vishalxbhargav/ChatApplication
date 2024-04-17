package com.vishalxbhargav.chatbackend.service.imp;

import com.vishalxbhargav.chatbackend.model.User;
import com.vishalxbhargav.chatbackend.repository.UserRepository;
import com.vishalxbhargav.chatbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CustomUserService implements UserDetailsService {
    @Autowired
    private  UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(email);
        if(user==null) throw new UsernameNotFoundException("User Not found with email "+email);
        List<GrantedAuthority> authorities=new ArrayList<>();
        return new  org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}
