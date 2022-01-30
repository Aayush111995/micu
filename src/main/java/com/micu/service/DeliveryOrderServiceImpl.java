package com.micu.service;

import com.micu.dao.AddressRepository;
import com.micu.dao.DeliveryOrderRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class DeliveryOrderServiceImpl {
    public static Logger logger = LoggerFactory.getLogger(DeliveryOrderServiceImpl.class);
    private DeliveryOrderRepository deliveryOrderRepository;

}
