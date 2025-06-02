package com.vsredshift.main.kyu5;

import java.util.HashMap;
import java.util.Map;

public class Scramble {

    public static void main(String[] args) {
        System.out.println("Scramble letters");
        System.out.println(scrambleAlternate("scriptjavx", "javascript"));
    }


    // My Submitted Solution
    private static boolean scramble(String str1, String str2) {
        Map<Character, Integer> lettersCountStr1 = getCharacterCountMap(str1);
        Map<Character, Integer> lettersCountStr2 = getCharacterCountMap(str2);
        for (Character character : lettersCountStr2.keySet()) {
            int currentWordCharCount = lettersCountStr2.get(character);
            int lettersCharCount = lettersCountStr1.getOrDefault(character, 0);
            if (currentWordCharCount > lettersCharCount) return false;
        }
        return true;
    }

    private static Map<Character, Integer> getCharacterCountMap(String letters) {
        Map<Character, Integer> lettersCountMap = new HashMap<>();
        for (int i = 0; i < letters.length(); i++) {
            char currentCharacter = letters.charAt(i);
            int count = lettersCountMap.getOrDefault(currentCharacter, 0);
            lettersCountMap.put(currentCharacter, count + 1);
        }
        return lettersCountMap;
    }


    //____________________________________________________________________________
    //  ALTERNATE SOULTION
    //____________________________________________________________________________
    public static boolean scrambleAlternate(String str1, String str2) {
        if (str2.length() > str1.length()) return false;
        for (String s: str2.split("")) {
            if (!str1.contains(s))  return false;
            str1 = str1.replaceFirst(s,"");
            System.out.println(str1);
        }

        return true;
    }

}
