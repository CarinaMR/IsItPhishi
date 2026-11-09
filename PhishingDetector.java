
import java.net.URI;

public class PhishingDetector {

    public static String analyze(String url) {

        if (url == null || url.trim().isEmpty()) {
            return "Please enter a URL.";
        }

        int score = 0;
        StringBuilder findings = new StringBuilder();

        try {
            URI uri = new URI(url.trim());
            String host = uri.getHost();

            if (host == null ||
                    (!"http".equalsIgnoreCase(uri.getScheme())
                    && !"https".equalsIgnoreCase(uri.getScheme()))) {
                return "Invalid URL. Include http:// or https://";
            }

            host = host.toLowerCase();

            // Layer 1: URL Structure

            // Check for common URL shorteners.
            if (host.equals("bit.ly") || host.equals("tinyurl.com")
                    || host.equals("t.co")) {
                score += 7;
                findings.append("Shortened URL: +7\n");
            }

            // Check if the URL is unusually long.
            if (url.length() > 75) {
                score += 5;
                findings.append("Unusually long URL: +5\n");
            }

            // Check for too many subdomains.
            String[] parts = host.split("\\.");
            if (parts.length > 4) {
                score += 7;
                findings.append("Excessive subdomains: +7\n");
            }

            // Check if the host is an IP address.
            if (host.matches("(\\d{1,3}\\.){3}\\d{1,3}")) {
                score += 3;
                findings.append("IP address used as host: +3\n");
            }

            // Count unusual symbols in the URL.
            int symbols = 0;

            for (char c : url.toCharArray()) {
                if (c == '@' || c == '_' || c == '%') {
                    symbols++;
                }
            }

            if (symbols >= 5) {
                score += 3;
                findings.append("Excessive symbols: +3\n");
            }

            // Layer 2: Domain Analysis

            int domainScore = 0;
            StringBuilder domainFindings = new StringBuilder();

            // Flag punycode domains for additional review.
            if (host.contains("xn--")) {
                domainScore += 7;
                domainFindings.append("Punycode domain: +7\n");
            }

            // Look for words sometimes used in phishing domains.
            String[] suspiciousWords = {
                "verify", "login", "secure", "update", "account"
            };

            for (String word : suspiciousWords) {
                if (host.contains(word)) {
                    domainScore += 3;
                    domainFindings.append(
                        "Suspicious domain word (" + word + "): +3\n");
                    break;
                }
            }

            // Check for unusually long domain names.
            if (host.length() > 30) {
                domainScore += 3;
                domainFindings.append("Long domain name: +3\n");
            }

            // Combine the scores from both layers.
            int totalScore = score + domainScore;

            return "URL: " + url
                    + "\nURL Structure: " + score + "/25"
                    + "\nDomain Analysis: " + domainScore + "/25"
                    + "\nPartial Score: " + totalScore + "/50"
                    + "\n\nURL Findings:\n"
                    + (findings.length() == 0
                        ? "No URL structure indicators found.\n"
                        : findings.toString())
                    + "\nDomain Findings:\n"
                    + (domainFindings.length() == 0
                        ? "No domain indicators found.\n"
                        : domainFindings.toString())
                    + "\nNote: This is a partial assessment, not a safety verdict.";

        } catch (Exception e) {
            return "Invalid URL.";
        }
    }
}
