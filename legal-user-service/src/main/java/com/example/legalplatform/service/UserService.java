package com.example.legalplatform.service;

import com.example.legalplatform.entity.User;

public interface UserService {
    User login(String username, String password);
    boolean register(String username, String password);
    boolean updateProfile(User user);
}