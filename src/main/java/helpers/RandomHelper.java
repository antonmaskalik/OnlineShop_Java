package helpers;

import java.security.SecureRandom;
import java.util.Random;

public class RandomHelper {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new SecureRandom();

    public static int getRandomInt(int min, int max) {
        if (min >= max) {
            int temp = min;
            min = max;
            max = temp;
        }
        return RANDOM.nextInt(max - min) + min;
    }

    public static String getRandomString(int length) {
        StringBuilder string = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            string.append(CHARACTERS.charAt(index));
        }
        return string.toString();
    }
}