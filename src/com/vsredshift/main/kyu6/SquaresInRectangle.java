package com.vsredshift.main.kyu6;

import java.util.ArrayList;
import java.util.List;

public class SquaresInRectangle {
    public static void main(String[] args) {

        System.out.println("Squares in a rectangle");
        System.out.println(sqInRect(33, 21));
        System.out.println("------------------------------------------------");

    }

    private static List<Integer> sqInRect(int lng, int wdth) {
        if (lng == wdth) return null;

        List<Integer> squares = new ArrayList<>();

        while (lng > 0 && wdth > 0) {
            squares.add(Math.min(lng, wdth));
            if (wdth > lng) wdth = wdth - lng;
            else lng = lng - wdth;
        }
        return squares;
    }
}
