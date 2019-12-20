package com.runsoft;

import org.apache.logging.log4j.*;

import org.apache.logging.log4j.core.config.Configurator;
import org.slf4j.LoggerFactory;

public class testLog4j {

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger("");
        //Configurator.setLevel(logger.getName(), Level.INFO);

        //下面的消息将被输出
        logger.info(testLog4j.class.getName());
        logger.warn("this is a warn");
        logger.error("this is an error");
        logger.fatal("this is a fatal");
    }
}
