package com.pintharuHomes.Backend.service;

import com.pintharuHomes.Backend.dto.TokenDto;
import com.pintharuHomes.Backend.dto.UserDto;
import com.pintharuHomes.Backend.entity.Role;

import java.util.List;

public interface UserService {

    UserDto getUserByUsername(String username);

    UserDto getUserById(Integer id);

    TokenDto getUserByToken(String token);

    List<UserDto> getAllUsersByRole(Role role);

    String deleteUser(Integer id);
}
