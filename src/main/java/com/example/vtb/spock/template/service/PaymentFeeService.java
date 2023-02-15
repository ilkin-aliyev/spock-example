package com.example.vtb.spock.template.service;

import com.example.vtb.spock.template.model.dto.PaymentFeeDto;
import com.example.vtb.spock.template.util.PaymentFeeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentFeeService {
    private final PaymentFeeUtil paymentFeeUtil;

    public PaymentFeeDto getFee(BigDecimal amount, String country) {
        if (amount.compareTo(BigDecimal.valueOf(100)) < 0) return new PaymentFeeDto(amount);
        var feeByCountry = paymentFeeUtil.getFeeByCountry(country);
        return new PaymentFeeDto(feeByCountry.multiply(amount));
    }

}
