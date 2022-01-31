package com.micu.model;

import lombok.Data;


@Data
public class InitPaymentResponse {
    private ResponseHead head;
    private PaytmInitRespBody body;
}

