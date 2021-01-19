package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.OrderRequest;
import com.upgrad.eshop.exceptions.APIException;
import org.springframework.stereotype.Component;

@Component
public class OrderValidatorImpl implements OrderValidator {

    @Override
    public void validateOrderRequest(OrderRequest orderRequest) throws APIException {
        if (orderRequest.getProductId() == null || orderRequest.getAddressId() == null) {
            throw new APIException("Fields shouldn’t be null or empty");
        }
    }

}
