package com.micu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InitRefundBody {
    private String txnTimestamp;
    private String orderId;
    private String mid;
    private String refId;
    private ResultInfo resultInfo;
    private String refundId;
    private String txnId;
    private String refundAmount;
    private List<RefundDetailInfoList> refundDetailInfoList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RefundDetailInfoList{
        private String rrn;
        private String refundType;
        private String payMethod;
        private String userCreditExpectedDate;
        private String userMobileNo;
        private String refundAmount;
        private String maskedCardNumber;
        private String cardScheme;
    }
}