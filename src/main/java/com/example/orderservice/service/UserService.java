package com.example.orderservice.service;

import com.example.orderservice.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userid);

    List<UserDto> getAllUsers() ;

    UserDto updateUser(Long userid, UserDto updatedUser);

    void deleteUser(Long userid);
}
