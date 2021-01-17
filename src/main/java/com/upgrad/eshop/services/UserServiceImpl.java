package com.upgrad.eshop.services;

import com.upgrad.eshop.daos.UserRepository;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.EmailAlreadyRegisteredException;
import com.upgrad.eshop.exceptions.UserNotFoundException;
import com.upgrad.eshop.exceptions.UsernameAlreadyRegisteredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

//  TODO: Define the acceptUserDetails(), findByUsername(), findById(), addUser()and saveUser() methods by
//        using the necessary logic as per the method name and by overriding them from the UserService interface

    @Override
    public User acceptUserDetails(RegisterRequest registerRequest) throws UsernameAlreadyRegisteredException, EmailAlreadyRegisteredException {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findById(Long id) throws UserNotFoundException {
        return null;
    }

    @Override
    public User addUser(RegisterRequest registerRequest) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public User getLoggedInUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUserName(userDetails.getUsername()).orElse(null);
    }
}
