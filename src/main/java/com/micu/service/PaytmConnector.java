package com.micu.service;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.micu.constant.MicuConstant;
import com.micu.enums.PG;
import com.micu.model.*;
import com.micu.util.RetrofitUtil;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.micu.constant.MicuConstant.*;

@Service
public class PaytmConnector implements BasePaymentConnector{
    public static Logger LOG = LoggerFactory.getLogger(PaytmConnector.class);
    private final PaytmCustomCheckoutInterface checkoutInterface;
    private final String baseUrl;
    @Value("${paytm.custom-checkout.website:website}")
    private String website;
    @Value("${paytm.custom-checkout.callbackUrl:test}")
    private String callbackUrl;
    @Autowired
    private Gson gson;

    public PaytmConnector(@Value("${paytm.custom-checkout.baseurl:https://securegw-stage.paytm.in}") String baseUrl) {
        Retrofit retrofit = RetrofitUtil.getPaytmRetrofit(baseUrl);
        checkoutInterface = retrofit.create(PaytmCustomCheckoutInterface.class);
        this.baseUrl = baseUrl;
    }

    @Override
    public Map<String, String> initiatePayment(Map<String, String> requestParams) {
        String paymentId = requestParams.get(MicuConstant.PAYMENT_ID);
        String customerId = requestParams.get(CUSTOMER_ID);
        String pspMid = requestParams.get(PSP_MID);
        String pspMidKey = requestParams.get(PSP_MID_KEY);
        String amount = requestParams.get(AMOUNT);
        String paytmSsoToken = null;
        if (requestParams.containsKey(PAYTM_SSO_TOKEN))
            paytmSsoToken = requestParams.get(PAYTM_SSO_TOKEN);

        LOG.info(String.format("Starting init payment in Paytm PG for payment id:%s and orderId:%s", paymentId, null));
        InitPaymentPayload payload = InitPaymentPayload.initPaymentPayload(website, pspMid, amount, paymentId,
                pspMidKey, callbackUrl, customerId, paytmSsoToken);

        LOG.info("Loading payload for init payment for payment id: {} is:  {}", paymentId,payload);
        try {
            Call<InitPaymentResponse> objectCall = checkoutInterface.initiatePayment(payload, pspMid, paymentId);
            Response<InitPaymentResponse> response = objectCall.execute();
            if (response.isSuccessful()) {
                InitPaymentResponse paymentResponse = response.body();
                ResultInfo resultInfo = paymentResponse.getBody().getResultInfo();
                if (!resultInfo.getResultStatus().equals("S")) {
                    String responseError = String.format(
                            "Paytm Payment init failed for payment id:%s and order:%s. Status code:%s and message:%s",
                            paymentId, null, resultInfo.getResultCode(), resultInfo.getResultMsg());
                    LOG.error(responseError);
                    return ImmutableMap.of("initPayment", "false", "message", responseError, "txnToken", null);
                } else {
                    String txnToken = paymentResponse.getBody().getTxnToken();
                    return ImmutableMap.of("initPayment", "true", "txnToken", txnToken, USED_MID, pspMid,
                            USED_PSP_BASE_URL, baseUrl);
                }
            }
            return ImmutableMap.of();
        } catch (IOException e) {
            LOG.error("Paytm initiatePayment call failed for id: {}", paymentId);
            return ImmutableMap.of("initPayment", "false", "message", "Timeout from paytm", "txnToken", null);
        }    }

    @Override
    public Map<String, String> checkPaymentStatus(Map<String, String> params) {
        String orderId = params.get(PAYMENT_ID);
        String pspMid = params.get(PSP_MID);
        String pspMidKey = params.get(PSP_MID_KEY);
        CheckStatusPayload payload = CheckStatusPayload.getPayload(pspMid, pspMidKey, orderId);
        try {
            Call<PaytmCheckStatusResponse> call = checkoutInterface.paymentStatus(payload);
            Response<PaytmCheckStatusResponse> responseBody = call.execute();
            if (!responseBody.isSuccessful()) {
                LOG.error("Paytm status check call failed: {}", orderId);
                return ImmutableMap.of();
            }
            String signatureValidation = responseBody.headers().get(SIGNATURE_VALIDATION);
            if (!SUCCESS.equals(signatureValidation)) {
                LOG.error("Paytm status check signature verification failed for payment id: {}",orderId);
                return ImmutableMap.of();
            }
            PaytmCheckStatusResponse response = responseBody.body();
            PaytmStatusRespBody body = response.getBody();
            String resultStatus = body.getResultInfo().getResultStatus();

            HashMap<String, String> responseMap = new HashMap<>();
            switch (resultStatus) {
                case "TXN_SUCCESS" -> {
                    LOG.info(
                            "Paytm Transaction success response for payment specify varchar length in column defination jpa id:{}"
                            , orderId);
                    responseMap.put(PSC_STATUS, SUCCESS);
                    responseMap.put(PSC_AMOUNT, body.getTxnAmount());
                    responseMap.put(PSC_TRACKING_ID, body.getTxnId());
                    responseMap.put(PSC_PAYLOAD, gson.toJson(body));
                }
                case "TXN_FAILURE" -> {
                    LOG.info("Paytm Transaction status response failed for payment id: {}", orderId);
                    responseMap.put(PSC_STATUS, FAILED);
                    responseMap.put(PSC_PAYLOAD, gson.toJson(body));
                }
                case "PENDING" -> {
                    LOG.info("Paytm Transaction status response pending for payment id: {}", orderId);
                    responseMap.put(PSC_STATUS, PENDING);
                }
            }
            return responseMap;
        } catch (IOException e) {
            LOG.error("Paytm status check call failed for id: {}",orderId);
            return ImmutableMap.of(PSC_STATUS, PENDING);
        }
    }

    @Override
    public Map<String, String> processPayment(Map<String, String> params) {
        return null;
    }

    @Override
    public PG pgName() {
        return PG.PAYTM;
    }

    @Override
    public Map<String, String> initiateRefund(Map<String, String> params) {
        String refundPaymentId = params.get(REFUND_PAYMENT_ID);
        String amount = params.get(AMOUNT);
        String paymentId = params.get(PAYMENT_ID);
        String transactionId = params.get(PAYMENT_TXN_ID);
        String pspMid = params.get(PSP_MID);
        String pspMidKey = params.get(PSP_MID_KEY);

        PaytmInitRefundBody refBody = PaytmInitRefundBody.builder().mid(pspMid).orderId(paymentId)
                .refId(refundPaymentId).txnId(transactionId).refundAmount(amount).build();
        PaytmInitRefundPayload payload = PaytmInitRefundPayload.getPayload(refBody, pspMidKey);
        try {
            Call<PaytmInitRefundResponse> call = checkoutInterface.initiateRefund(payload);
            Response<PaytmInitRefundResponse> responseBody = call.execute();
            if (!responseBody.isSuccessful()) {
                LOG.error("Paytm refund initiated call failed: {}", paymentId);
                return ImmutableMap.of();
            }
            String signatureValidation = responseBody.headers().get(SIGNATURE_VALIDATION);
            if (!SUCCESS.equals(signatureValidation)) {
                LOG.error("Paytm refund init signature verification failed for payment id: {}", paymentId);
                return ImmutableMap.of();
            }
            InitRefundBody refundBody = responseBody.body().getBody();
            String resultCode = refundBody.getResultInfo().getResultCode();
            String resultMsg = refundBody.getResultInfo().getResultMsg();
            if ("601".equals(resultCode)) {
                return ImmutableMap.of(REFUND_STATUS, PENDING, REFUND_TRACKING_ID, refundBody.getRefundId());
            }
            String message = String.format("Error occured:%s:%s, while initiating the refund for payment id:%s",
                    resultCode, resultMsg, paymentId);
            LOG.error(message);
            return ImmutableMap.of(REFUND_STATUS, FAILED, REFUND_TRACKING_ID, params.get(REFUND_PAYMENT_ID));
        } catch (IOException e) {
            LOG.error("Paytm initiate refund call failed for id: {}", refundPaymentId);
            return ImmutableMap.of(REFUND_STATUS, PENDING, REFUND_TRACKING_ID, refundPaymentId);
        }
    }

    @Override
    public Map<String, String> checkRefundStatus(Map<String, String> params) {
        String refundPaymentId = params.get(REFUND_PAYMENT_ID);
        String paymentId = params.get(PAYMENT_ID);
        String pspMid = params.get(PSP_MID);
        String pspMidKey = params.get(PSP_MID_KEY);
        RefundStatusReqBody reqBody = RefundStatusReqBody.builder().mid(pspMid).orderId(paymentId)
                .refId(refundPaymentId).build();
        PaytmRefundCheckPayload payload = PaytmRefundCheckPayload.getPayload(reqBody, pspMidKey);
        try {
            Call<PaytmInitRefundResponse> call = checkoutInterface.refundStatus(payload);
            Response<PaytmInitRefundResponse> responseBody = call.execute();

            if (!responseBody.isSuccessful()) {
                LOG.error("Paytm refund initiated call failed: {}", paymentId);
                return ImmutableMap.of();
            }
            String signatureValidation = responseBody.headers().get(SIGNATURE_VALIDATION);
            if (!SUCCESS.equals(signatureValidation)) {
                LOG.error("Paytm refund init signature verification failed for payment id: {}", paymentId);
                return ImmutableMap.of();
            }
            InitRefundBody refundBody = responseBody.body().getBody();
            String resultCode = refundBody.getResultInfo().getResultCode();
            String resultMsg = refundBody.getResultInfo().getResultMsg();

            if ("601".equals(resultCode)) {
                return ImmutableMap.of(REFUND_STATUS, PENDING, REFUND_MESSAGE, resultMsg, REFUND_TRACKING_ID,
                        refundBody.getRefundId());
            }
            if ("10".equals(resultCode)) {
                String bankRef;
                if (refundBody.getRefundDetailInfoList().isEmpty()) {
                    bankRef = "";
                } else {
                    InitRefundBody.RefundDetailInfoList detailInfoList = refundBody.getRefundDetailInfoList().get(0);
                    LOG.info("Another refund status params for payment id: {} are: {}", paymentId , detailInfoList.toString());
                    bankRef = Strings.isNotBlank(detailInfoList.getRrn()) ? detailInfoList.getRrn()
                            : detailInfoList.getRrn();
                }
                return ImmutableMap.of(REFUND_STATUS, SUCCESS, REFUND_TRACKING_ID, refundBody.getRefundId(),
                        REFUND_BANK_REF, bankRef);
            }
            return ImmutableMap.of(REFUND_STATUS, FAILED);
        } catch (IOException e) {
            LOG.error("Paytm checkRefundStatus failed for id: {}",refundPaymentId);
            return ImmutableMap.of(REFUND_STATUS, PENDING);
        }
    }
}
