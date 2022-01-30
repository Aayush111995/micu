package com.micu.service;

import com.micu.dao.OrderRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl {
    public static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private OrderRepository orderRepository;

}
