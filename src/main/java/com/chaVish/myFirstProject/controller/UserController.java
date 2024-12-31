package com.chaVish.myFirstProject.controller;
import com.chaVish.myFirstProject.repository.UserRepository;
import com.chaVish.myFirstProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //creating a new user
    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }
    //get all user
    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
//getting user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        return userRepository.findById(id).orElse(null);
    }
    //updating a user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id,@RequestBody User userDetails){
        return userRepository.findById(id).map(user->{
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }).orElse(null);
    }
    //deleting a user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id){
        userRepository.deleteById(id);
    }

}
