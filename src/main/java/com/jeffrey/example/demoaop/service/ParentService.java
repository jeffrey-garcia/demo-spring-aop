package com.jeffrey.example.demoaop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParentService extends AbstractService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ParentService.class);

    @Override
    public boolean execute(String s1, String s2) {
        LOGGER.debug("execute with 2 params ...");
        return true;
    }

}
