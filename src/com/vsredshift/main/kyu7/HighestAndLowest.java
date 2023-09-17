package com.vsredshift.main.kyu7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighestAndLowest {

    public static void main(String[] args) {
        System.out.println(highAndLow("3 2 1 5 3 10 12"));

        System.out.println("------------------------------------------------");
        System.out.println("High And Low");
        System.out.println(highAndLow("8 3 -5 42 -1 0 0 -9 4 7 4 -4"));

    }

    private static String highAndLow(String numbers) {
        String[] numbersAsList = numbers.split(" ");
        List<Integer> listAsNumbers = new ArrayList<>();
        for (String number : numbersAsList) listAsNumbers.add(Integer.parseInt(number));
        return Collections.max(listAsNumbers) + " " + Collections.min(listAsNumbers);
    }


}
