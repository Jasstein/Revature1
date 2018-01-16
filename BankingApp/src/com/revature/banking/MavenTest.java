package com.revature.banking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MavenTest {
    private static final Logger logger = LogManager.getLogger(MavenTest.class.getName());
    public static void main(String[] args) {
        logger.error("Error");
        System.out.println("Hello.");
    }

}
