package com.vsredshift.main.kyu7;

public class NextPerfectSquare {

    public static void main(String[] args) {
        System.out.println("My original solution: \n");
        System.out.println(findNextSquare(34));
        System.out.println(":::::::::::::::::::::::::::::");
        System.out.println("Alternative solutions: \n");
        System.out.println("Alternative 1");
        System.out.println(findNextSquareAlt1(36));
        System.out.println("Alternative 2 ");
        System.out.println(findNextSquareAlt2(34));
    }

    public static int findNextSquare(int sq) {
        double squareRoot = Math.sqrt(sq);
        if (sq % squareRoot != 0) {
            System.out.println("Not a square " + sq);
            return (int) Math.pow(Math.floor(squareRoot) + 1, 2);
        }
        return (int) Math.pow(squareRoot + 1, 2);
    }

    // Alternative Solutions
    public static long findNextSquareAlt1(long sq) {
        long root = (long) Math.sqrt(sq);
        return root * root == sq ? (root + 1) * (root + 1) : -1;
    }

    public static long findNextSquareAlt2(long sq) {
        return Math.sqrt(sq) % 1 != 0 ? -1 : (long) Math.pow(Math.sqrt(sq) + 1, 2);
    }
}
