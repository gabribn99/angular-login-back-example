package com.example.login.service.impl;

import com.example.login.entity.User;
import com.example.login.repository.UserRepository;
import com.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public User addUser(User user) {
        return repository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return repository.findByEmail(email);
    }
}
