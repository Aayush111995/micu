package com.micu.service;

import com.micu.dao.AddressRepository;
import com.micu.dao.UserPlanRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class UserPlanServiceImpl {
    public static Logger logger = LoggerFactory.getLogger(UserPlanServiceImpl.class);
    private UserPlanRepository userPlanRepository;

}
