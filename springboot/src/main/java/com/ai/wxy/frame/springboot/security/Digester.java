package com.ai.wxy.frame.springboot.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Digester{
    private final String algorithm;
    private final int iterations;
    public Digester(String algorithm, int iterations) {
        // eagerly validate the algorithm
        createDigest(algorithm);
        this.algorithm = algorithm;
        this.iterations = iterations;
    }
    public byte[] digest(byte[] value) {
        MessageDigest messageDigest = createDigest(algorithm);
        for (int i = 0; i < iterations; i++) {
            value = messageDigest.digest(value);
        }
        return value;
    }

    private static MessageDigest createDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No such hashing algorithm", e);
        }
    }
}
