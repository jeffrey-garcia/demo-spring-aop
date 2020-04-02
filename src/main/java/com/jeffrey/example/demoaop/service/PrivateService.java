package com.jeffrey.example.demoaop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class PrivateService extends AbstractService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrivateService.class);

    @Override
    public boolean execute(String s) {
        LOGGER.debug("execute ...");
        return true;
    }

    @Override
    public boolean execute(String s1, String s2) {
        LOGGER.debug("execute with 2 params ...");
        return true;
    }
}
