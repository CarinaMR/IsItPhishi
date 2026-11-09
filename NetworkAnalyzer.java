
import java.net.URI;

public class NetworkAnalyzer {

    public static String analyze(String originalUrl,
                                 String finalUrl,
                                 int redirects) {

        int score = 0;
        StringBuilder findings = new StringBuilder();

        try {
            URI original = new URI(originalUrl);
            String host = original.getHost();

            if (host == null) {
                return "Network: Invalid URL\n";
            }

            // Check whether there were several redirects.
            if (redirects >= 3) {
                score += 7;
                findings.append("Multiple redirects: +7\n");
            }

            // Check whether the final domain changed.
            if (redirects > 0 && finalUrl != null) {
                URI destination = new URI(finalUrl);
                String finalHost = destination.getHost();

                if (finalHost != null
                        && !host.equalsIgnoreCase(finalHost)) {
                    score += 7;
                    findings.append("Redirect to another domain: +7\n");
                }
            }

            // Look for an unusual port number.
            int port = original.getPort();

            if (port != -1 && port != 80 && port != 443) {
                score += 5;
                findings.append("Non-default port: +5\n");
            }

            // Check whether the URL uses HTTP.
            if ("http".equalsIgnoreCase(original.getScheme())) {
                score += 3;
                findings.append("Unencrypted HTTP URL: +3\n");
            }

            // Look for redirect parameters in the original URL.
            String query = original.getRawQuery();

            if (query != null) {
                String lowerQuery = query.toLowerCase();

                if (lowerQuery.contains("redirect=")
                        || lowerQuery.contains("redirect_url=")
                        || lowerQuery.contains("returnurl=")) {
                    score += 3;
                    findings.append("Redirect parameter detected: +3\n");
                }
            }

            return "Network: " + score + "/25\n"
                    + (findings.length() == 0
                        ? "No selected network indicators found.\n"
                        : findings.toString());

        } catch (Exception e) {
            return "Network: Could not analyze the supplied information.\n";
        }
    }
}
