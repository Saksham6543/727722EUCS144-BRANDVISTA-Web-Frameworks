package com.example.brandvista.usercredentials.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.brandvista.usercredentials.model.User;
import com.example.brandvista.usercredentials.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createNewUser(@RequestBody User user) {
        userService.registerUser(user);
        return "Account Created Successfully.";
    }


    @GetMapping("/get/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    public User getPost(@PathVariable("userId") int userId) {
        return userService.findById(userId);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> putMethodName(@PathVariable("userId") int userId, @RequestBody User updatedUser) {
        User existingUser = userService.findById(userId);
        if (existingUser != null) {
            existingUser.setEmailId(updatedUser.getEmailId());
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassWord(updatedUser.getPassWord());
            userService.registerUser(existingUser);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body( "User Updated Successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Given User-ID doesnot exist. Please Try Again.");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteQuery(@PathVariable("userId") int userId) {
        if (userService.findById(userId) != null) {
            userService.deleteUser(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body( "User Deleted Successfully.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( "Post Not Found.");
    }

}
