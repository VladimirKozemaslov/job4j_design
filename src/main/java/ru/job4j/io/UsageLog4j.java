package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte byteParam = 95;
        short shortParam = -30001;
        int intParam = 6666666;
        long longParam = 1111111111111111111L;
        float floatParam = 36.36F;
        double doubleParam = 366666666666666666666.36D;
        boolean boolParam = true;
        char charParam = 455;
        LOG.debug("byte : {}, short : {}, int : {}, long : {}, float : {}, double : {}, boolean : {}, char : {}",
                byteParam, shortParam, intParam, longParam, floatParam, doubleParam, boolParam, charParam);
    }
}