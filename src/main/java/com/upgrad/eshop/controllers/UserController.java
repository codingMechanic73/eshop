package com.upgrad.eshop.controllers;

import com.upgrad.eshop.entities.User;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.services.UserService;
import com.upgrad.eshop.utils.APIAuthorizer;
import com.upgrad.eshop.utils.Constants;
import com.upgrad.eshop.utils.EntityDtoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private APIAuthorizer apiAuthorizer;

    @Autowired
    private UserService userService;

    @Autowired
    private EntityDtoMapper entityDtoMapper;


    @GetMapping("/details")
    public ResponseEntity<?> getUserDetails(@RequestHeader(Constants.JwtToken.HEADER_KEY) String jwtToken) throws APIException {

        log.debug("Processing user details request");

        log.debug("Authorizing user. token [" + jwtToken + "]");
        apiAuthorizer.authorize(jwtToken);
        log.debug("User authorised");

        User user = userService.getLoggedInUser();
        log.debug("user details fetched for username " + user.getUserName());

        return ResponseEntity.ok(user);
    }

}
