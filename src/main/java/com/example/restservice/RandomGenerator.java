package com.example.restservice;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

@Component
public class RandomGenerator {

    private static Random random;
    private static int POSSIBLE_TILES = 26; //0-25 being A-Z and 26 being *
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();


    public RandomGenerator() {
        random = new Random(System.currentTimeMillis());
    }

    /**
     * Generates a random tile
     * @return the value of the generated tile
     */
    public static String nextTile() {
        int inc = random.nextInt(POSSIBLE_TILES);
        if (inc == 26) {
            return "*";
        }
        char c = 'A';
        c+= inc;
        return String.valueOf(c);
    }

    public static String generateUserToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
