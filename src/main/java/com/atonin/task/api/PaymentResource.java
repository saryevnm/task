package com.atonin.task.api;

import com.atonin.task.model.Payment;
import com.atonin.task.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PaymentResource {

    private final PaymentService paymentService;

    @PostMapping(value = "/pay")
    public String save(@RequestBody String request) throws JsonProcessingException {
        log.info("Request body for pay {}", request);
        ObjectMapper objectMapper = new ObjectMapper();
        Payment pay = objectMapper.readValue(request, Payment.class);
        return paymentService.addOrUpdate(pay);
    }

    @GetMapping(value = "/check")
    public String check(@RequestBody String request) throws JsonProcessingException {
        log.info("Request body for check {}", request);
        ObjectMapper objectMapper = new ObjectMapper();
        Payment payment = objectMapper.readValue(request, Payment.class);
        return paymentService.findAccount(payment);
    }
}
