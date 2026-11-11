import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AnalyzeServlet")
public class AnalyzeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Retrieve user-submitted URL from the form input
        String link = request.getParameter("link");
        
        // 2. Fetch/Calculate metrics from your backend logic
        // (Replace these variables with calls to your backend services/methods)
        int overallRiskScore = 0;             // Default set to 0
        String threatLevel = "N/A";           // Default set to N/A
        int urlStructurePoints = 0;          // Default set to 0
        int domainAnalysisPoints = 0;        // Default set to 0
        int webContentPoints = 0;            // Default set to 0

        /* 
           Example integration with Java backend:
           
           PhishingAnalyzerService service = new PhishingAnalyzerService();
           AnalysisResult result = service.analyze(link);
           
           overallRiskScore = result.getRiskScore();
           threatLevel = result.getThreatLevel();
           urlStructurePoints = result.getUrlStructureScore();
           domainAnalysisPoints = result.getDomainAnalysisScore();
           webContentPoints = result.getWebContentScore();
        */

        // 3. Attach attributes to request object for JSP rendering
        request.setAttribute("analyzedUrl", link);
        request.setAttribute("riskScore", overallRiskScore);
        request.setAttribute("threatLevel", threatLevel);
        request.setAttribute("urlStructureScore", urlStructurePoints);
        request.setAttribute("domainAnalysisScore", domainAnalysisPoints);
        request.setAttribute("webContentScore", webContentPoints);

        // 4. Forward to results.jsp to render the page
        request.getRequestDispatcher("/results.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Handle GET request by forwarding to POST handler or setting default attributes directly
        doPost(request, response);
    }
}
