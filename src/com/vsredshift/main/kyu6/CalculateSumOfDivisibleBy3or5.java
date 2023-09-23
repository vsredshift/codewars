package com.vsredshift.main.kyu6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CalculateSumOfDivisibleBy3or5 {
    public static void main(String[] args) {
        System.out.println("The count is: \n");
        System.out.println("By stream:");
        System.out.println(calculateSumOfDivisibleBy3or5UsingStream(20));
        System.out.println("By counter:");
        System.out.println(calculateSumOfDivisibleBy3or5(20));
        System.out.println("------------------------------------------------");

    }


    private static int calculateSumOfDivisibleBy3or5(int total) {
        int count = 0;
        if (total < 0) return -1;
        for (int i = 1; i <= total; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                count = count + i;
            }
        }
        return count;
    }

    private static int calculateSumOfDivisibleBy3or5UsingStream(int total) {
        if (total < 0) return -1;
        return IntStream.range(0, total + 1)
                .filter(n -> (n % 3 == 0) || (n % 5 == 0))
                .sum();
    }

}
