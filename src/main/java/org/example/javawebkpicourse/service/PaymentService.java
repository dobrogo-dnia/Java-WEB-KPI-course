package org.example.javawebkpicourse.service;

import org.example.javawebkpicourse.domain.payment.Payment;
import org.example.javawebkpicourse.domain.payment.PaymentTransaction;

public interface PaymentService {

    PaymentTransaction processPayment(Payment payment);

}
