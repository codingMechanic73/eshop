package com.upgrad.eshop.validators;

import com.upgrad.eshop.entities.Product;
import com.upgrad.eshop.exceptions.APIException;
import org.springframework.stereotype.Component;

@Component
public class ProductValidatorImpl implements ProductValidator {

    @Override
    public void validateProductRequest(Product productRequest) throws APIException {
        if (productRequest.getName() == null || productRequest.getName().isEmpty()
                || productRequest.getCategory() == null || productRequest.getCategory().isEmpty()
                || productRequest.getPrice() == null
                || productRequest.getDescription() == null || productRequest.getDescription().isEmpty()
                || productRequest.getManufacturer() == null || productRequest.getManufacturer().isEmpty()
                || productRequest.getAvailableItems() == null
                || productRequest.getImageUrl() == null || productRequest.getImageUrl().isEmpty()) {
            throw new APIException("Fields shouldn’t be null or empty");
        }
    }

}
