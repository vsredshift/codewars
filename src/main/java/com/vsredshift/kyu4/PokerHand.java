package main.java.com.vsredshift.kyu4;

import javax.sound.sampled.Port;
import java.util.*;
import java.util.stream.Collectors;

public class PokerHand {
    public static void main(String[] args) {
//        var sampleHand = new PokerHand("2D 3C 4S 5H AD");
//        System.out.println(sampleHand.getCardsByValue());
//        System.out.println(sampleHand.getCardsBySuit());
//        System.out.println(sampleHand.isStraight());

        var hand1 = new PokerHand("AD 7D 3D 4D 5D");
        var hand2 = new PokerHand("9D 3D QD JD TD");
        System.out.println(hand1.evaluateHandRank());
        System.out.println(hand2.evaluateHandRank());
        System.out.println(hand1.compareWith(hand2));

    }

    public enum Result {TIE, WIN, LOSS}

    public enum HandRank {
        ROYAL_FLUSH,
        STRAIGHT_FLUSH,
        FOUR_OF_A_KIND,
        FULL_HOUSE,
        FLUSH,
        STRAIGHT,
        THREE_OF_A_KIND,
        TWO_PAIR,
        ONE_PAIR,
        HIGH_CARD
    }

    private final List<Card> cards;

    public PokerHand(String hand) {
        this.cards = parseHand(hand);
        cards.sort(Collections.reverseOrder());
    }

    public List<Card> parseHand(String hand) {
        return Arrays.stream(hand.split(" "))
                .map(card -> new Card(
                        card.substring(0, 1).toUpperCase(),
                        card.substring(1, 2).toUpperCase()
                ))
                .collect(Collectors.toList());
    }

    // Get card values sorted descending (Ace high by default)
    public List<Integer> getSortedCardValues() {
        return getSortedCardValues(true);
    }

    // Get card values with configurable Ace high/low
    public List<Integer> getSortedCardValues(boolean aceHigh) {
        return cards.stream()
                .map(card -> card.getNumericValue(aceHigh))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    // Group cards by value
    public Map<String, List<Card>> getCardsByValue() {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::getValue));
    }

    // Group cards by suit
    public Map<String, List<Card>> getCardsBySuit() {
        return cards.stream()
                .collect(Collectors.groupingBy(Card::getSuit));
    }

    //private

    private boolean isConsecutive(List<Integer> values) {
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i) - 1 != values.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public HandRank evaluateHandRank() {
        if (isRoyalFlush()) return HandRank.ROYAL_FLUSH;
        if (isStraightFlush()) return HandRank.STRAIGHT_FLUSH;
        if (isFullHouse()) return HandRank.FULL_HOUSE;
        if (isFourOfAKind()) return HandRank.FOUR_OF_A_KIND;
        if (isFlush()) return HandRank.FLUSH;
        if (isStraight()) return HandRank.STRAIGHT;
        if (isThreeOfAKind()) return HandRank.THREE_OF_A_KIND;
        if (isTwoPair()) return HandRank.TWO_PAIR;
        if (isOnePair()) return HandRank.ONE_PAIR;
        return HandRank.HIGH_CARD;
    }

    private boolean isRoyalFlush() {
        return isStraightFlush() && isConsecutive(getSortedCardValues(true));
    }

    private boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    private boolean isFullHouse() {
        boolean hasThree = false;
        boolean hasTwo = false;

        for (List<Card> cards : getCardsByValue().values()) {
            if (cards.size() == 3) hasThree = true;
            if (cards.size() == 2) hasTwo = true;
        }
        return hasTwo && hasThree;
    }

    private boolean isFourOfAKind() {
        return getCardsByValue().values().stream().anyMatch(cards -> cards.size() == 4);
    }

    private boolean isFlush() {
        return getCardsBySuit().size() == 1;
    }

    // Check for straight with flexible Ace handling
    public boolean isStraight() {
        return isStraightAcesHigh() || isStraightAcesLow();
    }

    private boolean isStraightAcesHigh() {
        return isConsecutive(getSortedCardValues(true));
    }

    private boolean isStraightAcesLow() {
        if (cards.stream().anyMatch(card -> card.getValue().equals("A"))) {
            return isConsecutive(getSortedCardValues(false));
        }
            return false;
    }

    private boolean isThreeOfAKind() {
        return getCardsByValue().values().stream().anyMatch(cards -> cards.size() == 3);
    }

    private boolean isTwoPair() {
        int pairCount = 0;

        for (List<Card> cards : getCardsByValue().values()) {
            if (cards.size() == 2) pairCount++;
        }
        return pairCount == 2;
    }

    private boolean isOnePair() {
        return getCardsByValue().values().stream().anyMatch(cards -> cards.size() == 2);
    }

    public Result compareWith(PokerHand hand) {
        HandRank thisRank = evaluateHandRank();
        HandRank otherRank = hand.evaluateHandRank();
        System.out.println(thisRank.compareTo(otherRank));

        if (thisRank != otherRank) {
            return thisRank.compareTo(otherRank) < 0 ? Result.WIN : Result.LOSS;
        }
        return compareTieBreakers(hand, thisRank);
    }

    private Result compareTieBreakers(PokerHand other, HandRank rank) {
        switch (rank) {
            case STRAIGHT_FLUSH:
            case STRAIGHT:
            case FLUSH:
            case HIGH_CARD:
            default:
                return compareHighCards(other);
        }
    }

    private Result compareHighCards(PokerHand other) {
        List<Integer> thisValues = getSortedCardValues();
        List<Integer> otherValues = other.getSortedCardValues();

        for (int i = 0; i < thisValues.size(); i++) {
            if (!thisValues.get(i).equals(otherValues.get(i))) {
                return thisValues.get(i) > otherValues.get(i) ? Result.WIN : Result.LOSS;
            }
        }
        return Result.TIE;
    }

    @Override
    public String toString() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(" "));
    }

    public static class Card implements Comparable<Card> {

        private final String value;
        private final String suit;
        private final int numericValue;

        public Card(String value, String suit) {
            this.value = value;
            this.suit = suit;
            this.numericValue = convertToNumericValue(value);
        }

        private int convertToNumericValue(String value) {
            switch (value) {
                case "A": return 14;
                case "K": return 13;
                case "Q": return 12;
                case "J": return 11;
                case "T": return 10;
                default: return Integer.parseInt(value);
            }
        }

        // Getters
        public String getValue() { return value; }
        public String getSuit() { return suit; }
        public int getNumericValue() { return numericValue; }
        public int getNumericValue(boolean aceHigh) {
            return value.equals("A") ? (aceHigh ? 14 : 1) : numericValue;
        }

        @Override
        public int compareTo(Card other) {
            return Integer.compare(this.numericValue, other.numericValue);
        }

        @Override
        public String toString() {
            return value + suit;
        }

    }
}

