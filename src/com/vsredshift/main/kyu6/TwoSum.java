package com.vsredshift.main.kyu6;

import java.util.Arrays;

public class TwoSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 3, 4}, 6)));
        System.out.println(Arrays.toString(twoSum(new int[]{12, 13, 14}, 25)));
    }

    private static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i; j < numbers.length; j++) {
                System.out.println(numbers[i] + numbers[j]);
                if ((numbers[i] + numbers[j]) == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
