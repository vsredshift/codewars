package test.java.com.vsredshift;

import main.java.com.vsredshift.kyu4.BreadcrumbGenerator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Kyu4Test {
    @Test
    public void breadcrumbGeneratorTest() {

        String[] urls = new String[] {"mysite.com/pictures/holidays.html",
                "www.codewars.com/users/GiacomoSorbi?ref=CodeWars",
                "www.microsoft.com/docs/index.htm#top",
                "mysite.com/very-long-url-to-make-a-silly-yet-meaningful-example/example.asp",
                "www.very-long-site_name-to-make-a-silly-yet-meaningful-example.com/users/giacomo-sorbi"};

        String[] separators = new String[] {" : ", " / ", " * ", " > ", " + "};

        String[] answers = new String[] {"<a href=\"/\">HOME</a> : <a href=\"/pictures/\">PICTURES</a> : <span class=\"active\">HOLIDAYS</span>",
                "<a href=\"/\">HOME</a> / <a href=\"/users/\">USERS</a> / <span class=\"active\">GIACOMOSORBI</span>",
                "<a href=\"/\">HOME</a> * <span class=\"active\">DOCS</span>",
                "<a href=\"/\">HOME</a> > <a href=\"/very-long-url-to-make-a-silly-yet-meaningful-example/\">VLUMSYME</a> > <span class=\"active\">EXAMPLE</span>",
                "<a href=\"/\">HOME</a> + <a href=\"/users/\">USERS</a> + <span class=\"active\">GIACOMO SORBI</span>"};

        for (int i = 0; i < 5; i++) {
            System.out.println(" \nTest with : " + urls[i]);
            String actual = BreadcrumbGenerator.generateBreadcrumb(urls[i], separators[i]);
            if (!actual.equals(answers[i])) {
                System.out.printf("Expected : %s%n", reformat(answers[i]));
                System.out.printf("Actual :   %s%n", reformat(actual));
            }
            assertEquals(answers[i], actual);
        }
    }
    String reformat(String s) { return s.replace("<","<"); }
}

