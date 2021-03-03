package com.example.sam.user;

import com.example.sam.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll()
    {
        return userRepository.findAll();
    }

    public User findbyId(int id) {
        Optional<User> user= userRepository.findById(Integer.valueOf(id));
        if(!user.isPresent())
            throw new UserNotFoundException(String.format("user with id %d not found",id));
        return user.get();
    }
}
