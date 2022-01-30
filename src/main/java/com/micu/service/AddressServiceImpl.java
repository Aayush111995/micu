package com.micu.service;

import com.micu.dao.AddressRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl {
    public static Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
    private AddressRepository addressRepository;

}
