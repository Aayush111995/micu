package com.micu.model;


import com.google.gson.Gson;
import com.micu.config.MyApplicationContextProvider;
import com.micu.service.PaytmChecksum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckStatusPayload {
    private StatusBody body;
    private HeadRequest head;

    @SneakyThrows
    public static CheckStatusPayload getPayload(String mid, String midKey, String orderId) {
        StatusBody statusBody = new StatusBody(mid, orderId);
        Gson gson = MyApplicationContextProvider.getBean(Gson.class);
        String signature = PaytmChecksum.generateSignature(gson.toJson(statusBody), midKey);
        return new CheckStatusPayload(statusBody, new HeadRequest(signature));
    }
}

@AllArgsConstructor
@NoArgsConstructor
class StatusBody {
    private String mid;
    private String orderId;
}
