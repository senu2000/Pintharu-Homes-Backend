package com.pintharuHomes.Backend.service.impl;

import com.pintharuHomes.Backend.dto.TokenDto;
import com.pintharuHomes.Backend.dto.UserDto;
import com.pintharuHomes.Backend.entity.*;
import com.pintharuHomes.Backend.exception.ResourceNotFoundException;
import com.pintharuHomes.Backend.mapper.TokenMapper;
import com.pintharuHomes.Backend.mapper.UserMapper;
import com.pintharuHomes.Backend.repository.CartRepository;
import com.pintharuHomes.Backend.repository.OrderDetailRepository;
import com.pintharuHomes.Backend.repository.TokenRepository;
import com.pintharuHomes.Backend.repository.UserRepository;
import com.pintharuHomes.Backend.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private TokenRepository tokenRepository;

    private CartRepository cartRepository;

    private OrderDetailRepository orderDetailRepository;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

//    @Override
//    public UserDto getUserByUsername(String username) {
//        Optional<User> optionalUser = userRepository.findByUsername(username);
//        User user = optionalUser.orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with username: " + username));
//
//        return UserMapper.mapToUserDto(user);
//    }

    @Override
    public UserDto getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        UserDto userDto;
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userDto = UserMapper.mapToUserDto(user);
        } else {
            userDto = null;
        }
        return userDto;
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
    public Integer getUserCount(Role role) {
        List<User> users = userRepository.findUsersByRole(role);
        return users.size();
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

        List<Cart> cartUsers = cartRepository.findByUser(user);
        if (cartUsers != null && !cartUsers.isEmpty()) {
            for (Cart cart : cartUsers) {
                cart.setUser(null);
                cartRepository.delete(cart);
            }
        }

        List<OrderDetail> orderUsers = orderDetailRepository.findByUser(user);
        if (orderUsers != null && !orderUsers.isEmpty()) {
            for (OrderDetail orderDetail : orderUsers) {
                orderDetail.setUser(null);
                orderDetailRepository.delete(orderDetail);
            }
        }

        userRepository.deleteById(id);

        return "User and all associations deleted successfully";
    }
}
