package com.micu.model;

import com.google.gson.Gson;
import com.micu.service.PaytmChecksum;
import lombok.*;
import org.apache.logging.log4j.util.Strings;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class InitPaymentPayload {

    private CustomPaymentRequestBody body;
    private CustomApiRequestHead head;

    @SneakyThrows
    public static InitPaymentPayload initPaymentPayload(String website, String mid, String amount,
                                                        String orderId, String midKey, String callbackUrl, String customerId, String paytmSsoToken) {
        CustomPaymentRequestBody body = CustomPaymentRequestBody.builder()
                .requestType("Payment")
                .mid(mid)
                .orderId(orderId)
                .websiteName(website)
                .txnAmount(new TxnAmount(amount, "INR"))
                .userInfo(new UserInfo(customerId))
                .callbackUrl(callbackUrl)
                .paytmSsoToken(Strings.isBlank(paytmSsoToken) ? null : paytmSsoToken)
                .build();

        Gson gson = new Gson();

        String signature = PaytmChecksum.generateSignature(gson.toJson(body), midKey);
        return new InitPaymentPayload(body, new CustomApiRequestHead(signature));
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class CustomPaymentRequestBody {
        private String requestType;
        private String mid;
        private String orderId;
        private String websiteName;
        private TxnAmount txnAmount;
        private UserInfo userInfo;
        private String callbackUrl;
        private String paytmSsoToken;
    }
}


@AllArgsConstructor
@NoArgsConstructor
class TxnAmount {
    private String value;
    private String currency;
}

@AllArgsConstructor
@NoArgsConstructor
class UserInfo {
    private String custId;
}

@AllArgsConstructor
@NoArgsConstructor
class CustomApiRequestHead {
    private String signature;
}