package com.micu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FetchPaymentOptionsPaytmRequest {

    private Head head;
    private Body body;

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Head{
        private String tokenType;
        private String token;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static class Body{
        private String mid;
    }
}
