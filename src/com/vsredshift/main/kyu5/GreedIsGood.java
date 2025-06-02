package com.vsredshift.main.kyu5;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GreedIsGood {
    public static void main(String[] args) {
        int[] testArray = {1,4,5,1,1};
        System.out.println(greedy(testArray));
        System.out.println(greedyModuloVersion(testArray));
        System.out.println(greedyWeightedVersion(testArray));
        System.out.println(greedyCollectionsVersion(testArray));
    }

    // My Submitted Solution
    public static int greedy(int[] dice) {
        ArrayList<Integer> countList = convertArrayToArrayList(dice);
        Map<Integer, Integer> countMap = getCountMap(countList);

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            switch (entry.getKey()) {
                case 1:
                    count = count + (entry.getValue() * 100);
                    if (entry.getValue() > 2) count = count + 700;
                    break;
                case 5:
                    count = count + (entry.getValue() * 50);
                    if (entry.getValue() > 2) count = count + 350;
                    break;
                default:
                    if (entry.getValue() > 2) {
                        count = count + (entry.getKey() * 100);
                    }
            }
        }

        return count;
    }

    private static Map<Integer, Integer> getCountMap(ArrayList<Integer> countList) {
        Map<Integer, Integer> numbersDone = new HashMap<>();
        for (int i : countList) {
            numbersDone.put(i, countOccurrences(countList, i));
        }
        return numbersDone;
    }

    private static ArrayList<Integer> convertArrayToArrayList(int[] dice) {
        ArrayList<Integer> countList = new ArrayList<>();
        for (int i : dice) countList.add(i);
        return countList;
    }

    static int countOccurrences(ArrayList<Integer> countList, int numberToCheck) {
        return Collections.frequency(countList, numberToCheck);
    }


    //------------------------------------------------------------------------------//
    //          ALTERNATIVE SOLUTIONS                                               //
    //------------------------------------------------------------------------------//

    public static int greedyModuloVersion(int[] dice) {
        int[] n = new int[7];
        for (int d : dice) n[d]++;
        return n[1]/3*1000 + n[1]%3*100 + n[2]/3*200 + n[3]/3*300 + n[4]/3*400 + n[5]/3*500 + n[5]%3*50 + n[6]/3*600;
    }


    public static int greedyWeightedVersion(int[] dice){
        int res = 0;
        int[] count = new int[]{0, 0, 0, 0, 0, 0};
        int[] weight = new int[]{100, 0, 0, 0, 50, 0};
        int[] weight3 = new int[]{1000, 200, 300, 400, 500, 600};

        for (int die : dice) count[die-1]++;

        for (int i = 0; i < count.length; i++) res+=(count[i]/3*weight3[i]) + (count[i]%3 * weight[i]);

        return res;
    }


    public static int greedyCollectionsVersion(int[] dice){
        Map<Integer, Long> collect = Arrays.stream(dice).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        int score = 0;
        for (Map.Entry<Integer, Long> e : collect.entrySet()) {
            switch (e.getKey()) {
                case 1: score += ( ( e.getValue() >= 3) ? 1000 : 0) + (e.getValue() % 3) * 100; break;
                case 2: score += ( ( e.getValue() >= 3) ? 200 : 0); break;
                case 3: score += ( ( e.getValue() >= 3) ? 300 : 0); break;
                case 4: score += ( ( e.getValue() >= 3) ? 400 : 0); break;
                case 5: score += ( ( e.getValue() >= 3) ? 500 : 0) + (e.getValue() % 3) * 50; break;
                case 6: score += ( ( e.getValue() >= 3) ? 600 : 0); break;
            }
        }
        return score;
    }
}
