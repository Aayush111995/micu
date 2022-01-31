package com.micu.model;

import lombok.Data;

@Data
public class PaytmCheckStatusResponse {
    private PaytmStatusRespBody body;
    private ResponseHead head;
}