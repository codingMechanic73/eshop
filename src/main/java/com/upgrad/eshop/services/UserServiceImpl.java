package com.upgrad.eshop.services;

import com.upgrad.eshop.daos.UserRepository;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.EmailAlreadyRegisteredException;
import com.upgrad.eshop.exceptions.UserNotFoundException;
import com.upgrad.eshop.exceptions.UsernameAlreadyRegisteredException;
import com.upgrad.eshop.utils.DtoEntityMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private DtoEntityMapper dtoEntityMapper;

    private final UserRepository userRepository;

    @Override
    public User acceptUserDetails(RegisterRequest registerRequest) throws UsernameAlreadyRegisteredException, EmailAlreadyRegisteredException {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new EmailAlreadyRegisteredException("Try any other email address, this email address is already registered!");
        }
        if (userRepository.findByUserName(registerRequest.getUserName()).isPresent()) {
            throw new UsernameAlreadyRegisteredException("Try any other username, this username is already registered!");
        }
        return saveUser(dtoEntityMapper.convertToUserEntity(registerRequest));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username).get();
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("This username has not been registered!"));
    }

    @Override
    public User addUser(RegisterRequest registerRequest) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User getLoggedInUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUserName(userDetails.getUsername()).orElse(null);
    }

}
