package com.jeffrey.example.demoaop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractService implements Service {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

    public boolean execute(String s) {
        LOGGER.debug("execute: {}", s);
        return true;
    }

    public abstract boolean execute(String s1, String s2);

}
