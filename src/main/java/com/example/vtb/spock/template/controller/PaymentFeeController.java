package com.example.vtb.spock.template.controller;

import com.example.vtb.spock.template.model.dto.PaymentFeeDto;
import com.example.vtb.spock.template.service.PaymentFeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payment-fee")
public class PaymentFeeController {
    private final PaymentFeeService paymentFeeService;

    @GetMapping
    public PaymentFeeDto getFee(@RequestParam BigDecimal amount, @RequestParam String country) {
        return paymentFeeService.getFee(amount, country);
    }
}
