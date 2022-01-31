package com.micu.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaytmStatusRespBody {
    private ResultInfo resultInfo;
    private String txnId;
    private String bankTxnId;
    private String orderId;
    private String txnAmount;
    private String txnType;
    private String gatewayName;
    private String bankName;
    private String mid;
    private String paymentMode;
    private String refundAmt;
    private String txnDate;
}
