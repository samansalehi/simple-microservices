package com.example.sam.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> users()
    {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User user(@PathVariable int id)
    {
        return userService.findbyId(id);
    }

}
