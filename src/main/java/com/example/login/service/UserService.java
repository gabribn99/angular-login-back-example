package com.example.login.service;

import com.example.login.entity.User;

public interface UserService {

    public User addUser(User user);
    public User getUserByEmail(String email);
}
