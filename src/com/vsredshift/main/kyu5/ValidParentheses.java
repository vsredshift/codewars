package com.vsredshift.main.kyu5;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ValidParentheses {

    public static void main(String[] args) {
//        System.out.println(validParentheses("(())"));
        System.out.println(validParenthesesRegex("(342134!(&&/)())"));
    }

    public static boolean validParentheses(String parens) {
        int count = 0;
        for (int i = 0; i < parens.length(); i++) {
            if (parens.charAt(i) == '(') count++;
            if (parens.charAt(i) == ')') count--;
            if (count < 0) return false;
        }
        return count == 0;
    }

    // REGEX Solutiom
    public static boolean validParenthesesRegex(String parens) {
        try {
            Pattern.compile(parens.replaceAll("[^()]", ""));
            return true;
        } catch (PatternSyntaxException e) {
            return false;
        }

    }
}
