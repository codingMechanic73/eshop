package com.upgrad.eshop.validators;

import com.upgrad.eshop.daos.UserRepository;
import com.upgrad.eshop.dtos.LoginRequest;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserValidatorImpl implements UserValidator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validateUserRegistration(RegisterRequest registerRequest) throws APIException {

        if (registerRequest.getUserName() == null || registerRequest.getUserName().isEmpty()
                || registerRequest.getEmail() == null || registerRequest.getEmail().isEmpty()
                || registerRequest.getFirstName() == null || registerRequest.getFirstName().isEmpty()
                || registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()
                || registerRequest.getPhoneNumber() == null || registerRequest.getPhoneNumber().isEmpty()) {
            throw new APIException("Fields shouldn’t be null or empty");
        }

        if (!Pattern.matches(Constants.RegexPattern.EMAIL, registerRequest.getEmail())) {
            throw new APIException("Invalid email-id format!");
        }

        if (!Pattern.matches(Constants.RegexPattern.PHONE, registerRequest.getPhoneNumber())) {
            throw new APIException("Invalid contact number!");
        }

        if (registerRequest.getPassword().length() < 8) {
            throw new APIException("Weak password!");
        }
    }

    @Override
    public void validateUserLogin(LoginRequest loginRequest) throws APIException {
        if (loginRequest.getUserName() != null
                && userRepository.findByUserName(loginRequest.getUserName()).isEmpty()) {
            throw new APIException("This username has not been registered!");
        }
        if (loginRequest.getUserName() == null || loginRequest.getUserName().isEmpty()
                || loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty())
            throw new APIException("Fields shouldn’t be null or empty");
    }

}
