package com.upgrad.eshop.controllers;

import com.upgrad.eshop.dtos.LoginRequest;
import com.upgrad.eshop.dtos.LoginResponse;
import com.upgrad.eshop.dtos.RegisterRequest;
import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.exceptions.BadCredentialsException;
import com.upgrad.eshop.exceptions.EmailAlreadyRegisteredException;
import com.upgrad.eshop.exceptions.UsernameAlreadyRegisteredException;
import com.upgrad.eshop.security.JwtTokenProvider;
import com.upgrad.eshop.services.UserService;
import com.upgrad.eshop.utils.EntityDtoMapper;
import com.upgrad.eshop.validators.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    /*
     * Credentials for ADMIN
     * username: admin
     * email: admin@upgrad.com
     * password: password
     */
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
            throws APIException, BadCredentialsException {
        log.debug("Processing login request from " + loginRequest.getUserName());

        log.debug("Started login request validation for " + loginRequest.getUserName());
        userValidator.validateUserLogin(loginRequest);
        log.debug("Complete login request validation for " + loginRequest.getUserName());

        log.debug("Started authentication for " + loginRequest.getUserName());
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserName(),
                            loginRequest.getPassword()
                    )
            );
            log.debug("Authentication successful.");

            log.debug("Generating Jwt token for " + loginRequest.getUserName());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenProvider.generateToken(authentication);
            log.debug("Jwt Token generated successfully. Token [" + token + "]");

            final LoginResponse loginResponse = new LoginResponse(loginRequest.getUserName(), "Success", token);
            return ResponseEntity.ok(loginResponse);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid Credentials!");
        }
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) throws APIException, UsernameAlreadyRegisteredException, EmailAlreadyRegisteredException {
        log.debug("Processing register request");

        log.debug("Started register request validation");
        userValidator.validateUserRegistration(registerRequest);
        log.debug("Complete register request validation for " + registerRequest.getUserName());

        User user = userService.acceptUserDetails(registerRequest);
        return ResponseEntity.ok(user);
    }

}
