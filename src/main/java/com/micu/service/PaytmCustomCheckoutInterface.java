package com.micu.service;


import com.micu.model.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PaytmCustomCheckoutInterface {

    @POST("/theia/api/v1/initiateTransaction")
    Call<InitPaymentResponse> initiatePayment(@Body InitPaymentPayload initPaymentPayload, @Query("mid") String mid, @Query("orderId") String orderId);

    @POST("/merchant-status/api/v1/getPaymentStatus")
    Call<PaytmCheckStatusResponse> paymentStatus(@Body CheckStatusPayload payload);

    @POST("/refund/apply")
    Call<PaytmInitRefundResponse> initiateRefund(@Body PaytmInitRefundPayload payload);

    @POST("/v2/refund/status")
    Call<PaytmInitRefundResponse> refundStatus(@Body PaytmRefundCheckPayload payload);


    @POST("/theia/api/v1/token/create")
    Call<AccessTokenPaytmResponse> getAccessToken(@Query("mid") String mid, @Query("referenceId") String referenceId,
                                                  @Body AccessTokenPaytmRequest payload);

    @POST("/theia/api/v2/fetchPaymentOptions")
    Call<Object> fetchPaymentOptions(@Query("mid") String mid, @Query("referenceId") String referenceId,
                                     @Body FetchPaymentOptionsPaytmRequest payload);

}
