package com.micu.service;

import com.micu.dao.AddressRepository;
import com.micu.dao.RefundRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class RefundServiceImpl {
    public static Logger logger = LoggerFactory.getLogger(RefundServiceImpl.class);
    private RefundRepository refundRepository;

}
