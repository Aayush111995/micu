package com.micu.model;

import com.google.gson.Gson;

import com.micu.config.MyApplicationContextProvider;
import com.micu.service.PaytmChecksum;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaytmInitRefundPayload {
    private PaytmInitRefundBody body;
    private HeadRequest head;

    @SneakyThrows
    public static PaytmInitRefundPayload getPayload(PaytmInitRefundBody body, String midKey) {
        body.setTxnType("REFUND");
        Gson gson = MyApplicationContextProvider.getBean(Gson.class);
        String signature = PaytmChecksum.generateSignature(gson.toJson(body), midKey);
        return new PaytmInitRefundPayload(body, new HeadRequest(signature));
    }
}

