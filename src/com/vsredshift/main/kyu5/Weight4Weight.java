package com.vsredshift.main.kyu5;

import java.util.*;
import java.util.stream.Collectors;

public class Weight4Weight {

    public static void main(String[] args) {
        System.out.println("Weight For Weight");
        System.out.println(orderWeightAlternate2("12 32 43 21 34 13767 "));
        System.out.println(orderWeightAlternate2("59544965313 231 231 2312004990"));

    }

    // My Submitted Solution
    public static String orderWeight(String weights) {
        List<String> weightList = new ArrayList<>(Arrays.asList(weights.trim().split(" ")));
        weightList.removeAll(Collections.singleton(""));

        weightList.sort((o1, o2) -> {
            if (sumDigits(Long.parseLong(o1)) > sumDigits(Long.parseLong(o2))) {
                return 1;
            } else if (Objects.equals(sumDigits(Long.parseLong(o1)), sumDigits(Long.parseLong(o2)))) {
                return o1.compareTo(o2);
            } else return -1;
        });
        StringBuilder outputString = new StringBuilder();
        for (String element: weightList) {
            outputString.append(element).append(" ");
        }

        return outputString.toString().trim();
    }

    private static Long sumDigits(Long number) {
        long sum = 0L;
        while (number != 0) {
            sum = sum + (number % 10);
            number = number/10;
        }
        return sum;
    }


    //____________________________________________________________________________
    //  ALTERNATE SOULTIONS
    //____________________________________________________________________________
    public static String orderWeightAlternate(String string) {
        String[] split = string.split(" ");
        Arrays.sort(split, (a, b) -> {
            int aWeight = a.chars().map(Character::getNumericValue).sum();
            int bWeight = b.chars().map(Character::getNumericValue).sum();
            return aWeight - bWeight != 0 ? aWeight - bWeight : a.compareTo(b);
        });
        return String.join(" ", split);
    }

    public static String orderWeightAlternate2(String str) {
        return Arrays.stream(str.trim().split(" "))
                .sorted()
                .sorted(Comparator.comparingInt(i -> i.chars().map(Character::getNumericValue).sum()))
                .collect(Collectors.joining(" "));
    }

}
