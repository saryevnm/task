package com.atonin.task.service;

import com.atonin.task.model.Payment;

public interface PaymentService {

    String addOrUpdate(Payment user);
    String findAccount(Payment payment);

}
