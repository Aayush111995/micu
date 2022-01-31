package com.micu.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SignatureValidationService {

    protected static final Logger LOG = LoggerFactory.getLogger(SignatureValidationService.class);
    static final TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() {
    };
    @Autowired
    private ObjectMapper objectMapper;


    public boolean signatureValidation(String payload, String midKey) {
        HashMap<String, Object> payloadHashMap;
        try {
            payloadHashMap = objectMapper.readValue(payload, typeRef);
            if (!payloadHashMap.containsKey("body") || !payloadHashMap.containsKey("head")) {
                LOG.info("No head body found in payload");
                return false;
            }
            String body = objectMapper.writeValueAsString(payloadHashMap.get("body"));
            HashMap<String, String> head = (HashMap<String, String>) payloadHashMap.get("head");
            String signature = head.get("signature");
            return PaytmChecksum.verifySignature(body, midKey, signature);
        } catch (Exception e) {
            LOG.info("Some error occurred while validating signature");
        }
        return false;
    }

    public boolean paytmSignatureValidation(String payload, String midKey) {
        HashMap<String, Object> payloadHashMap;
        try {
            payloadHashMap = objectMapper.readValue(payload, typeRef);
            if (!payloadHashMap.containsKey("body") || !payloadHashMap.containsKey("head")) {
                LOG.info("No head body found in payload");
                return false;
            }
            String body = objectMapper.writeValueAsString(payloadHashMap.get("body"));
            HashMap<String, String> head = (HashMap<String, String>) payloadHashMap.get("head");
            String signature = head.get("signature");
            return PaytmChecksum.verifySignature(body, midKey, signature);
        } catch (Exception e) {
            LOG.info("Some error occurred while validating signature");
        }
        return false;
    }


}
