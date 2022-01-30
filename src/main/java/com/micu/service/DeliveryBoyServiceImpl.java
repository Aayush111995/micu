package com.micu.service;

import com.micu.dao.DeliveryBoyRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliveryBoyServiceImpl {
    public static Logger logger = LoggerFactory.getLogger(DeliveryBoyServiceImpl.class);
    private DeliveryBoyRepository deliveryBoyRepository;

}
