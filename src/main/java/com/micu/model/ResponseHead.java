package com.micu.model;

import lombok.Data;

@Data
public class ResponseHead {
    private String responseTimestamp;
    private String version;
    private String signature;
}
