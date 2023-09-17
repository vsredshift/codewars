package com.vsredshift.main.kyu7;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseWords {

    public static void main(String[] args) {
        System.out.println(reverseWords("Merry  Christmas everyone"));
    }

    public static String reverseWords(final String original) {
        String[] array = original.split(" ");
        System.out.println(Arrays.stream(array).filter(s -> !s.isEmpty()).collect(Collectors.toList()));
        if (array.length == 0)
            return original;

        int i = 0;
        for (String string : array) {
            array[i] = new StringBuilder(string).reverse().toString();
            i++;
        }
        return String.join(" ", array);
    }

}
