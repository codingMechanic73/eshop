package com.upgrad.eshop.validators;

import com.upgrad.eshop.dtos.ShippingAddressDto;
import com.upgrad.eshop.exceptions.APIException;
import com.upgrad.eshop.utils.Constants;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ShippingAddressValidatorImpl implements ShippingAddressValidator {

    @Override
    public void validateShippingAddressRequest(ShippingAddressDto addressRequest) throws APIException {
        if (addressRequest.getName().isEmpty() || addressRequest.getName() == null
                || addressRequest.getPhone().isEmpty() || addressRequest.getPhone() == null
                || addressRequest.getCity().isEmpty() || addressRequest.getCity() == null
                || addressRequest.getLandmark().isEmpty() || addressRequest.getLandmark() == null
                || addressRequest.getStreet().isEmpty() || addressRequest.getStreet() == null
                || addressRequest.getState().isEmpty() || addressRequest.getState() == null
                || addressRequest.getZipcode().isEmpty() || addressRequest.getZipcode() == null) {
            throw new APIException("Fields shouldn’t be null or empty");
        }

        if (!Pattern.matches(Constants.RegexPattern.ZIPCODE, addressRequest.getZipcode())) {
            throw new APIException("Invalid zip code!");
        }

        if (!Pattern.matches(Constants.RegexPattern.PHONE, addressRequest.getPhone())) {
            throw new APIException("Invalid contact number!");
        }
    }
}
