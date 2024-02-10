package com.blogapp3.blogapp3.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encodePassword = new BCryptPasswordEncoder();
        System.out.println(encodePassword.encode("user"));
    }
}
