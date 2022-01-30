package com.micu.service;

import com.micu.dao.CartRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl {
    public static Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    private CartRepository cartRepository;

}
