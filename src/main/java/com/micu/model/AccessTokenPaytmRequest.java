package com.micu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenPaytmRequest {

    private Head head;
    private Body body;

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Data
    public static class Body{
        private String mid;
        private String referenceId;
        private String paytmSsoToken;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Head{
        private String tokenType;
        private String token;
    }
}
