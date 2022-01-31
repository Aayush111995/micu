package com.micu.interceptor;


import com.micu.config.MyApplicationContextProvider;
import com.micu.service.SignatureValidationService;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.io.IOException;

import static com.micu.constant.MicuConstant.*;

public class SignatureValidateInterceptor implements Interceptor {
    protected static final Logger LOG = LoggerFactory.getLogger(SignatureValidateInterceptor.class);
    private SignatureValidationService signatureValidationService;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Environment env = MyApplicationContextProvider.getBean(Environment.class);

        Request originalRequest = chain.request();
        Response response = chain.proceed(originalRequest);

        ResponseBody body = response.body();
        if (body == null)
            return response;

        String originalBody = body.string();

        //todo: fix this.
        //   boolean validationResponse = validateResponse(originalBody, env.getProperty("paytm.custom-checkout.mid-key"));
        boolean validationResponse = true;
        return response.newBuilder()
                .body(ResponseBody.create(body.contentType(), originalBody))
                .header(SIGNATURE_VALIDATION, validationResponse ? SUCCESS : FAILED)
                .build();
    }

    private boolean validateResponse(String originalBody, String midKey) {
        if (signatureValidationService == null) {
            signatureValidationService = MyApplicationContextProvider.getBean(SignatureValidationService.class);
        }
        try {
            return signatureValidationService.paytmSignatureValidation(originalBody, midKey);
        } catch (Exception e) {
            LOG.error("Signature verification failed in paytm interceptor. Error: {}", e.getMessage());
        }
        return false;
    }
}
