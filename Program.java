

public class Program {

    public static void main(String[] args) {

        // Fictional URL for testing our detection rules.
        String url = "http://verify-account.example.test:8081"
                + "/login?redirect=https://other.example.test";

        // Sample HTML that triggers some content checks.
        String html = """
                <html>
                    <body>
                        <p>Urgent action required</p>
                        <form>
                            <input type="password">
                        </form>
                        <form>
                            <input type="text">
                        </form>
                        <script>window.open('popup')</script>
                    </body>
                </html>
                """;

        // Simulated redirect information for testing.
        String finalUrl = "https://other.example.test";
        int redirects = 3;

        System.out.println("=== IS IT PHISHI? ===\n");

        System.out.println("LAYERS 1 AND 2");
        System.out.println(PhishingDetector.analyze(url));

        System.out.println("\nLAYER 3");
        System.out.println(WebContentAnalyzer.analyze(url, html));

        System.out.println("\nLAYER 4");
        System.out.println(NetworkAnalyzer.analyze(
                url, finalUrl, redirects));
    }
}
