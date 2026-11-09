
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebContentAnalyzer {

    public static String analyze(String url, String html) {
        int score = 0;
        StringBuilder findings = new StringBuilder();

        if (html == null || html.isEmpty()) {
            return "Web Content: Not checked\n";
        }

        String content = html.toLowerCase();

        // Check for a password field.
        if (content.matches("(?s).*<input\\b[^>]*type\\s*=\\s*['\"]?password\\b.*")) {
            score += 7;
            findings.append("Password field detected: +7\n");
        }

        // Count forms that might collect user information.
        int forms = count(content, "<form\\b");

        if (forms >= 2) {
            score += 7;
            findings.append("Multiple forms detected: +7\n");
        }

        // Count links to other domains.
        int externalLinks = 0;

        try {
            String originalHost = new URI(url).getHost();

            Matcher links = Pattern.compile(
                "href\\s*=\\s*['\"](https?://[^'\"]+)['\"]"
            ).matcher(content);

            while (links.find()) {
                String linkHost = new URI(links.group(1)).getHost();

                if (originalHost != null && linkHost != null
                        && !linkHost.equalsIgnoreCase(originalHost)) {
                    externalLinks++;
                }
            }
        } catch (Exception e) {
            // Skip links we cannot read.
        }

        if (externalLinks >= 5) {
            score += 3;
            findings.append("Many external links: +3\n");
        }

        // Look for simple script-related warning signs.
        if (content.contains("window.open(")
                || content.contains("eval(")
                || content.contains("document.write(")) {
            score += 3;
            findings.append("Script warning indicator: +3\n");
        }

        // Look for suspicious language on the page.
        String[] suspiciousWords = {
            "verify your account",
            "urgent action required",
            "account suspended",
            "confirm your password",
            "unusual login activity"
        };

        for (String word : suspiciousWords) {
            if (content.contains(word)) {
                score += 5;
                findings.append("Suspicious webpage text: +5\n");
                break;
            }
        }

        return "Web Content: " + score + "/25\n"
                + (findings.length() == 0
                    ? "No selected web content indicators found.\n"
                    : findings.toString());
    }

    // Count how many times a pattern appears.
    private static int count(String text, String pattern) {
        Matcher matcher = Pattern.compile(pattern).matcher(text);
        int total = 0;

        while (matcher.find()) {
            total++;
        }

        return total;
    }
}


