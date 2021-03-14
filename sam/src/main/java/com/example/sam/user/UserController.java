package com.example.sam.user;

import com.example.sam.UserNotFoundException;
import com.example.sam.post.Post;
import com.example.sam.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

    @Autowired
    PostRepository postRepository;

    @GetMapping("/users")
    public List<User> users() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> findUser(@PathVariable int id) {
        User user= userService.findbyId(id);
        EntityModel<User> resource = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.
                linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).users());
        resource.add(linkTo.withRel("all-users"));
        return resource;
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

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createUserPost(@PathVariable int id, @RequestBody Post post) {
        User user = userService.findbyId(id);
        if (user == null) {
            throw new UserNotFoundException("id: " + id);
        }
        post.setUser(user);
        postRepository.save(post);
        URI location= ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
