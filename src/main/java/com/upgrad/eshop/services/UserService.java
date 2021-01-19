package com.upgrad.eshop.services;

import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.EmailAlreadyRegisteredException;
import com.upgrad.eshop.exceptions.UserNotFoundException;
import com.upgrad.eshop.exceptions.UsernameAlreadyRegisteredException;

public interface UserService {

    User acceptUserDetails(RegisterRequest registerRequest)
            throws UsernameAlreadyRegisteredException, EmailAlreadyRegisteredException;

    User findByUsername(String username);

    User findById(Long id) throws UserNotFoundException;

    User addUser(RegisterRequest registerRequest) throws UsernameAlreadyRegisteredException;

    User saveUser(User user);

    User getLoggedInUser();
}
