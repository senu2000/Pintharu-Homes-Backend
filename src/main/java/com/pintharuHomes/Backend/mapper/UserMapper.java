package com.pintharuHomes.Backend.mapper;

import com.pintharuHomes.Backend.dto.UserDto;
//import com.pintharuHomes.Backend.entity.Role;
import com.pintharuHomes.Backend.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getPhone_number(),
                user.getAddress(),
                user.getRole().name()
        );
    }

//    public static User mapToUser(UserDto userDto){
//        Role role = Role.valueOf(userDto.getRole());
//        if (role == null) {
//            throw new IllegalArgumentException("Invalid role: " + userDto.getRole());
//        }
//        return new User(
//                userDto.getId(),
//                userDto.getUsername(),
//                userDto.getPassword(),
//                userDto.getPhone_number(),
//                userDto.getAddress(),
//                role
//        );
//    }
}
