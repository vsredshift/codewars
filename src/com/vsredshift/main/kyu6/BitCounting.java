package com.vsredshift.main.kyu6;

public class BitCounting {
    public static void main(String[] args) {
        System.out.println(countBits(10));
        System.out.println(countBitsBitCount(10));
        System.out.println(countBitsFilter(10));
        System.out.println(countBitsModulo(10));
    }

    // Submitted Solution 2021
    private static int countBits(int n) {
        String num = Integer.toBinaryString(n);
        int count = 0;

        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }

    // Alternate Solutions

    private static int countBitsBitCount(int n){
        return Integer.bitCount(n);
    }

    private static int countBitsModulo(int n){
        int ret = n % 2;
        while ((n /= 2) > 0) ret += n % 2;
        return ret;
    }

    private static int countBitsFilter(int n){
        return (int) Integer.toBinaryString(n).chars()
                .filter(c -> c == '1')
                .count();
    }

}
