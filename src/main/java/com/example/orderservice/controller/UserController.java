package com.example.orderservice.controller;

import com.example.orderservice.dto.UserDto;
import com.example.orderservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserService userService;

    //Build Add User REST API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //Build Get User REST API
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userid) {
        UserDto userDto = userService.getUserById(userid);
        return ResponseEntity.ok(userDto);
    }

    //Build Get All User REST API
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //Build Update User REST API
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userid, @RequestBody UserDto updatedUser) {
        UserDto userDto = userService.updateUser(userid, updatedUser);
        return ResponseEntity.ok(userDto);
    }

    //Build Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userid) {
        userService.deleteUser(userid);
        return ResponseEntity.ok("User deleted successfully");
    }
}
