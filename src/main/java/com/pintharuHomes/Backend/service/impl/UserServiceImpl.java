package com.pintharuHomes.Backend.service.impl;

import com.pintharuHomes.Backend.dto.TokenDto;
import com.pintharuHomes.Backend.dto.UserDto;
import com.pintharuHomes.Backend.entity.Role;
import com.pintharuHomes.Backend.entity.Token;
import com.pintharuHomes.Backend.entity.User;
import com.pintharuHomes.Backend.exception.ResourceNotFoundException;
import com.pintharuHomes.Backend.mapper.TokenMapper;
import com.pintharuHomes.Backend.mapper.UserMapper;
import com.pintharuHomes.Backend.repository.TokenRepository;
import com.pintharuHomes.Backend.repository.UserRepository;
import com.pintharuHomes.Backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private TokenRepository tokenRepository;

    @Override
    public UserDto getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with username: " + username));

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with ID: " + id));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public TokenDto getUserByToken(String token) {
        Optional<Token> optionalToken = tokenRepository.findByToken(token);
        Token details = optionalToken.orElseThrow(() -> new ResourceNotFoundException("token is not exist"));
        return TokenMapper.mapToTokenDto(details);
    }

    @Override
    public List<UserDto> getAllUsersByRole(Role role) {
        List<User> users = userRepository.findUsersByRole(role);
        return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with ID: " + id));

        // Delete associated tokens
        List<Token> tokens = user.getTokens();
        if (tokens != null && !tokens.isEmpty()) {
            for (Token token : tokens) {
                token.setUser(null); // Remove reference to user to avoid constraint violation
                tokenRepository.delete(token);
            }
        }

        userRepository.deleteById(id);

        return "User and associated tokens deleted successfully";
    }
}
