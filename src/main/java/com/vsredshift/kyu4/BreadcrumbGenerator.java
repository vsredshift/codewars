package main.java.com.vsredshift.kyu4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BreadcrumbGenerator {
    public static void main(String[] args) {
        System.out.println(generateBreadcrumb("www.mysite.com/users/Joe-Soap?ref=Codewars", " : "));
        System.out.println(generateBreadcrumb("mysite.com/very-long-url-to-make-a-silly-yet-meaningful-example/example.htm", " > "));
    }
    public static String generateBreadcrumb(String url, String separator) {
        List<String> parts = decomposeUrl(url);

        StringBuilder breadcrumb = new StringBuilder();
        String currentPath =  "";

        if (parts.size() == 1) {
            return "<span class=\"active\">HOME</span>";
        }

        breadcrumb.append("<a href=\"/\">HOME</a>");

        for (int i = 1; i < parts.size() - 1; i++) {
            currentPath += "/" + parts.get(i);
            breadcrumb.append(separator)
                    .append(formatLink(currentPath, parts.get(i)));
        }

        if (parts.size() > 1) {
            String lastPart = cleanLastSegment(parts.get(parts.size() - 1));
            breadcrumb.append(separator)
                    .append(formatActiveSegment(lastPart));
        }

        return breadcrumb.toString();
    }

    private static String formatLink(String path, String segment) {
        return String.format("<a href=\"%s/\">%s</a>", path, stripLongUrl(segment));
    }

    private static String formatActiveSegment(String segment) {
        return String.format("<span class=\"active\">%s</span>",
                stripLongUrl(segment));
    }

    private static String cleanLastSegment(String segement) {
        return segement.replaceAll("\\.(htm|html|php|asp|aspx|jsp)$", "");
    }

    public static List<String> decomposeUrl(String url) {
        return Arrays.stream(url.replaceAll("^(https?://|ftp://|mailto:|tel:)", "").replaceAll("^/+|/+$", "")
                .split("[#?]")[0]
                .split("/+"))
                .filter(part -> !part.isEmpty() && !part.startsWith("index"))
                .collect(Collectors.toList());
    }

    public static String stripLongUrl(String longUrl) {
        if (longUrl.length() < 31) return longUrl.replaceAll("[-_]", " ").toUpperCase();

        final Set<String> WORDS_TO_IGNORE = Set.of(
                "the", "of", "in", "from", "by", "with",
                "and", "or", "for", "to", "at", "a"
        );

        return Arrays.stream(longUrl.replaceAll("[-_]", " ").split("\\s+"))
                .filter(word -> !word.isEmpty())
                .filter(word -> !WORDS_TO_IGNORE.contains(word.toLowerCase()))
                .map(word -> word.substring(0, 1).toUpperCase())
                .collect(Collectors.joining());
    }
}

