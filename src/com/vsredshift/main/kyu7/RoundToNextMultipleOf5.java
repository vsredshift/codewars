package com.vsredshift.main.kyu7;

public class RoundToNextMultipleOf5 {

    public static void main(String[] args) {
        System.out.println(roundToNext5(10323));
    }

    public static int roundToNext5(int number) {
        while (number % 5 != 0) {
            number++;
        }
        return number;
    }
}
