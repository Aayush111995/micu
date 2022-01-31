package com.micu.service;

import com.micu.enums.PG;

import java.util.Map;

public interface BasePaymentConnector {
    Map<String, String> initiatePayment(Map<String, String> requestParams);

    Map<String, String> checkPaymentStatus(Map<String, String> params);

    Map<String, String> processPayment(Map<String, String> params);

    PG pgName();

    Map<String, String> initiateRefund(Map<String, String> params);

    Map<String, String> checkRefundStatus(Map<String, String> params);

}
