package com.micu.model;

import lombok.Data;

@Data
public class PaytmInitRefundResponse {
    private ResponseHead head;
    private InitRefundBody body;
}


