package com.vsredshift.main.kyu5;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
//TODO To be completed

public class FactorialDecomposition {

    public static void main(String[] args) {
        System.out.println("The prime numbers are : " + primeNumbers(120));
        System.out.println("Factorial is " + calculateFactorial(8));
        System.out.println(howManyTimesDivisible(calculateFactorial(5), 5));
    }

//    public static String decomp(int n) {
//        long factorial = calculateFactorial(n);
//        int divisible = howManyTimesDivisible(factorial, 2);
//        return String.valueOf(divisible);
//    }

    private static long calculateFactorial(int n) {
        return LongStream.rangeClosed(1, n)
                .reduce(1, (long x, long y) -> x * y);
    }

    private static HashMap<Integer, Integer> howManyTimesDivisible(long number, int divider) {
        HashMap<Integer, Integer> divisibleCount = new HashMap<>();
        Integer count = 0;

        while (number % divider == 0) {
            number = number / divider;
            System.out.println("number after division...." + number);
            count++;
        }
        divisibleCount.put(divider, count);
        return divisibleCount;
    }

    private static List<Integer> primeNumbers(int n) {
        return IntStream.rangeClosed(2, n)
                .filter(FactorialDecomposition::isPrime).boxed()
                .collect(Collectors.toList());
    }

    private static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, (int) (Math.sqrt(number)))
                .allMatch(n -> number % n != 0);
    }
}
