package com.zhuangqf.learn.netty;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTest {

    private static final Logger logger = LoggerFactory.getLogger(Log4jTest.class);

    @Test
    public void testLogger(){
        logger.debug("This is a log");
    }

}
