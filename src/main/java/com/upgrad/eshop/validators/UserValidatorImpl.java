package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.LoginRequest;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.exceptions.APIException;
import org.springframework.stereotype.Component;

@Component
public class UserValidatorImpl implements UserValidator{
    @Override
    public void validateUserRegistration(RegisterRequest registerRequest) throws APIException {

    }

    @Override
    public void validateUserLogin(LoginRequest loginRequest) throws APIException {

    }
}
