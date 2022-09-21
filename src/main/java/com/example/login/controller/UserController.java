package com.example.login.controller;

import com.example.login.entity.User;
import com.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/user")
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            User addedUser = service.addUser(user);
            response.put("message", "User " + addedUser.getUsername() + " was registered successfully");
            response.put("object", user);
        } catch (DataIntegrityViolationException e) {
            response.put("error", "This email is already registered");
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<Map<String, Object>>(response, status);
    }

    @PostMapping("/user/login")
    public ResponseEntity<Map<String, Object>> logUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        try {
            User foundUser = service.getUserByEmail(user.getEmail());
            if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
                response.put("message", "User " + foundUser.getUsername() + " logged in!!");
                response.put("object", foundUser);
            } else {
                response.put("error", "Invalid login");
                status = HttpStatus.BAD_REQUEST;
            }
        } catch (DataIntegrityViolationException e) {
            response.put("error", "Couldn't login");
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(response, status);
    }
}
