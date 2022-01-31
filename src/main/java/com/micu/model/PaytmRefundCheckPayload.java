package com.micu.model;

import com.google.gson.Gson;

import com.micu.config.MyApplicationContextProvider;
import com.micu.service.PaytmChecksum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaytmRefundCheckPayload {
    private RefundStatusReqBody body;
    private HeadRequest head;

    @SneakyThrows
    public static PaytmRefundCheckPayload getPayload(RefundStatusReqBody body, String midKey) {
        Gson gson = MyApplicationContextProvider.getBean(Gson.class);
        String signature = PaytmChecksum.generateSignature(gson.toJson(body), midKey);
        return new PaytmRefundCheckPayload(body, new HeadRequest(signature));
    }
}
