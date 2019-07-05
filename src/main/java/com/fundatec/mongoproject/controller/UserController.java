package com.fundatec.mongoproject.controller;

import com.fundatec.mongoproject.domain.User;
import com.fundatec.mongoproject.service.UserService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity getUsersByUsername(@PathVariable String username) {
        List<User> users = userService.getUserByUsername(username);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity updateUser(@PathVariable String username, @RequestBody Document updates) {
        userService.updateUser(username, updates);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity deleteUser(@PathVariable String username) {
        userService.deleteUserByName(username);
        return new ResponseEntity(HttpStatus.OK);
    }
}
