package com.micu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaytmInitRefundBody {
    private String mid;
    private String txnType;
    private String orderId;
    private String txnId;
    private String refId;
    private String refundAmount;
}
