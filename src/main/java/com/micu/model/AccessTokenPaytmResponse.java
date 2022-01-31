package com.micu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccessTokenPaytmResponse {

    private Body body;
    private Head head;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Body{
        private ResultInfo resultInfo;
        private String accessToken;
        private Object extraParamsMap;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Head{
        private String responseTimestamp;
        private String version;
    }
}
