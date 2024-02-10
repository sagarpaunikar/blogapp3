package com.blogapp3.blogapp3.Service.impl;

import com.blogapp3.blogapp3.Repository.UserRepository;
import com.blogapp3.blogapp3.entities.CustomUser;
import com.blogapp3.blogapp3.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail);
        if(user == null){
            throw new UsernameNotFoundException("User not found with the username or email");
        }
        return new CustomUser(user);
    }
}
