package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.LoginRequest;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.exceptions.APIException;

public interface UserValidator {

    void validateUserRegistration(RegisterRequest registerRequest) throws APIException;

    void validateUserLogin(LoginRequest loginRequest) throws APIException;

}
