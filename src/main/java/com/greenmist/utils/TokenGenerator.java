package com.greenmist.utils;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by eckob on 10/23/2016.
 */

//see http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
@Component
public class TokenGenerator {

    private static final SecureRandom random = new SecureRandom();

    public static String generateToken() {
        return new BigInteger(260, random).toString(32);
    }
}
