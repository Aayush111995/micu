package com.micu.service;

import com.micu.dao.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl {
    public static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserRepository userRepository;

}
