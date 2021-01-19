package com.upgrad.eshop.controllers;

import com.upgrad.eshop.dtos.ShippingAddressDto;
import com.upgrad.eshop.entities.ShippingAddress;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.exceptions.ProductNotFoundException;
import com.upgrad.eshop.exceptions.ShippingAddressNotFoundException;
import com.upgrad.eshop.services.ShippingAddressService;
import com.upgrad.eshop.utils.APIAuthorizer;
import com.upgrad.eshop.utils.Constants;
import com.upgrad.eshop.utils.EntityDtoMapper;
import com.upgrad.eshop.validators.ShippingAddressValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController()
@RequestMapping("/")
public class ShippingAddressController {

    @Autowired
    private APIAuthorizer apiAuthorizer;

    @Autowired
    private ShippingAddressValidator shippingAddressValidator;

    @Autowired
    private ShippingAddressService shippingAddressService;

    @Autowired
    private EntityDtoMapper entityDtoMapper;

    @PostMapping("/user-addresses")
    public ResponseEntity<?> addShippingAddress(@RequestBody ShippingAddressDto addressRequest,
                                                @RequestHeader(Constants.JwtToken.HEADER_KEY) String jwtToken) throws APIException, ProductNotFoundException, ShippingAddressNotFoundException {
        log.debug("Processing shipping address request");

        log.debug("Authorizing user for ROLE_USER. token [" + jwtToken + "]");
        apiAuthorizer.authorizeFor(Constants.Roles.USER, jwtToken);
        log.debug("User authorised for ROLE_USER");

        log.debug("Started shipping address request validation");
        shippingAddressValidator.validateShippingAddressRequest(addressRequest);
        log.debug("Completed shipping address request validation");

        ShippingAddress shippingAddress = shippingAddressService.addAddress(addressRequest);
        return ResponseEntity.ok(entityDtoMapper.convertToShippingAddressResponse(shippingAddress));
    }

}
