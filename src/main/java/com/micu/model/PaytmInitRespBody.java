package com.micu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaytmInitRespBody {
    private ResultInfo resultInfo;
    public String txnToken;
    private Boolean isPromoCodeValid;
    private Boolean authenticated;
}
