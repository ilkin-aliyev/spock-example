package com.example.vtb.spock.template.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.example.vtb.spock.template.model.enums.Country.AZERBAIJAN;
import static com.example.vtb.spock.template.model.enums.Country.TURKEY;

@Component
public class PaymentFeeUtil {

    public BigDecimal getFeeByCountry(String country) {
        if (country.toUpperCase().equals(AZERBAIJAN.name())) return BigDecimal.valueOf(1);
        else if (country.toUpperCase().equals(TURKEY.name())) return BigDecimal.valueOf(1.5);
        else return BigDecimal.valueOf(2);
    }
}
