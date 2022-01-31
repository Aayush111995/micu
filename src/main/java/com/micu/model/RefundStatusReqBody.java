package com.micu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RefundStatusReqBody {
    private String mid;
    private String orderId;
    private String refId;
}
