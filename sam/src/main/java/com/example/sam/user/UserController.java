package com.example.sam.user;

import com.example.sam.UserNotFoundException;
import com.example.sam.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> users() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public User deleteUser(@PathVariable int id) {
        return userService.findbyId(id);
    }

    @DeleteMapping("/users/{id}")
    public void user(@PathVariable int id) {
        userService.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> user(@Valid @RequestBody User user) {
        User savedUser = userService.creatUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @GetMapping("/users/{id}/posts")
    public List<Post> userPosts(@PathVariable int id) {
        User user = userService.findbyId(id);
        if (user == null) {
            throw new UserNotFoundException("id: " + id);
        }
        return user.getPosts();
    }


}
