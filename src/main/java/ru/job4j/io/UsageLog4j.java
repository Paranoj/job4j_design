package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Egor";
        byte age = 25;
        char sex = 'M';
        short height = 179;
        float weight = 78.5F;
        boolean married = false;
        long wealth = 999999999;
        double honesty = 0.000001D;
        LOG.debug("User info name : {}, age : {}, sex : {}, height : {},"
                        + " weight : {}, married : {}, wealth : {}, honesty : {}",
                name, age, sex, height, weight, married, wealth, honesty);
    }
}
