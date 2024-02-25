package com.sgq.shortlink.admin.toolkit;

import java.util.Random;

/**
 * 分组ID随机生成器
 */
public final class RandomStringUtil {
    // 字符源，包括大写字母、小写字母和数字
    private static final char[] CHAR_SOURCE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    // Random对象用于生成随机数
    private static final Random random = new Random();

    /**
     * 生成随机分组ID
     *
     * @param length 字符串长度
     * @return 分组ID
     */
    public static String generateRandomString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be a positive number");
        }

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHAR_SOURCE[random.nextInt(CHAR_SOURCE.length)]);
        }
        return sb.toString();
    }

    /**
     * 生成6位随机分组ID
     *
     * @return 分组ID
     */
    public static String generateRandomString() {
        return generateRandomString(6);
    }

    public static void main(String[] args) {
        // 生成一个6位长度的随机字符串
        String randomString = generateRandomString(6);
        System.out.println(randomString);
    }
}
