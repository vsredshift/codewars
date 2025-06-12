package test.java.com.vsredshift;

import main.java.com.vsredshift.kyu4.BreadcrumbGenerator;
import main.java.com.vsredshift.kyu4.PokerHand;
import org.junit.Test;

import static main.java.com.vsredshift.kyu4.PokerHand.Result;
import static org.junit.Assert.assertEquals;

public class Kyu4Test {

    // Generate Breadcrumbs

    @Test
    public void breadcrumbGeneratorTest() {

        String[] urls = new String[]{"mysite.com/pictures/holidays.html",
                "www.codewars.com/users/GiacomoSorbi?ref=CodeWars",
                "www.microsoft.com/docs/index.htm#top",
                "mysite.com/very-long-url-to-make-a-silly-yet-meaningful-example/example.asp",
                "www.very-long-site_name-to-make-a-silly-yet-meaningful-example.com/users/giacomo-sorbi",
                " www.agcpartners.co.uk/"};

        String[] separators = new String[]{" : ", " / ", " * ", " > ", " + ", " * "};

        String[] answers = new String[]{"<a href=\"/\">HOME</a> : <a href=\"/pictures/\">PICTURES</a> : <span class=\"active\">HOLIDAYS</span>",
                "<a href=\"/\">HOME</a> / <a href=\"/users/\">USERS</a> / <span class=\"active\">GIACOMOSORBI</span>",
                "<a href=\"/\">HOME</a> * <span class=\"active\">DOCS</span>",
                "<a href=\"/\">HOME</a> > <a href=\"/very-long-url-to-make-a-silly-yet-meaningful-example/\">VLUMSYME</a> > <span class=\"active\">EXAMPLE</span>",
                "<a href=\"/\">HOME</a> + <a href=\"/users/\">USERS</a> + <span class=\"active\">GIACOMO SORBI</span>",
                "<span class=\"active\">HOME</span>"
        };

        for (int i = 0; i < 6; i++) {
            System.out.println(" \nTest with : " + urls[i]);
            String actual = BreadcrumbGenerator.generateBreadcrumb(urls[i], separators[i]);
            if (!actual.equals(answers[i])) {
                System.out.printf("Expected : %s%n", answers[i]);
                System.out.printf("Actual :   %s%n", actual);
            }
            assertEquals(answers[i], actual);
        }
    }


    // Ranking Poker Hands

    private Result loss = Result.LOSS;
    private Result win = Result.WIN;
    private Result tie = Result.TIE;

    @Test
    public void PokerHandRankingTest() {
        Test("Highest straight flush wins", loss, "2H 3H 4H 5H 6H", "KS AS TS QS JS");
        Test("Straight flush wins of 4 of a kind", win, "2H 3H 4H 5H 6H", "AS AD AC AH JD");
        Test("Highest 4 of a kind wins", win, "AS AH 2H AD AC", "JS JD JC JH 3D");
        Test("4 Of a kind wins of full house", loss, "2S AH 2H AS AC", "JS JD JC JH AD");
        Test("Full house wins of flush", win, "2S AH 2H AS AC", "2H 3H 5H 6H 7H");
        Test("Highest flush wins", win, "AS 3S 4S 8S 2S", "2H 3H 5H 6H 7H");
        Test("Flush wins of straight", win, "2H 3H 5H 6H 7H", "2S 3H 4H 5S 6C");
        Test("Equal straight is tie", tie, "2S 3H 4H 5S 6C", "3D 4C 5H 6H 2S");
        Test("Straight wins of three of a kind", win, "2S 3H 4H 5S 6C", "AH AC 5H 6H AS");
        Test("3 Of a kind wins of two pair", loss, "2S 2H 4H 5S 4C", "AH AC 5H 6H AS");
        Test("2 Pair wins of pair", win, "2S 2H 4H 5S 4C", "AH AC 5H 6H 7S");
        Test("Highest pair wins", loss, "6S AD 7H 4S AS", "AH AC 5H 6H 7S");
        Test("Pair wins of nothing", loss, "2S AH 4H 5S KC", "AH AC 5H 6H 7S");
        Test("Highest card loses", loss, "2S 3H 6H 7S 9C", "7H 3C TH 6H 9S");
        Test("Highest card wins", win, "4S 5H 6H TS AC", "3S 5H 6H TS AC");
        Test("Equal cards is tie", tie, "2S AH 4H 5S 6C", "AD 4C 5H 6H 2C");
    }

    private void Test(String description, Result expected, String playerHand, String opponentHand) {
        PokerHand player = new PokerHand(playerHand);
        PokerHand opponent = new PokerHand(opponentHand);
        assertEquals(description + ":", expected, player.compareWith(opponent));
    }

}

