package com.micu.service;

import com.micu.dao.PaymentRepository;
import com.micu.entity.Payment;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class PaymentServiceImpl {
    public static Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private PaymentRepository paymentRepository;
}
