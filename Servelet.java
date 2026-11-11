import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AnalyzeServlet")
public class AnalyzeServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Retrieve the link input using the 'name' attribute ("link")
        String userLink = request.getParameter("link");
        
        // Do something with the link (e.g., print it to the console)
        System.out.println("Received link: " + userLink);
        
        // Optional: Send a response back to the user
        response.setContentType("text/html");
        response.getWriter().println("<h3>Successfully analyzed link: " + userLink + "</h3>");
    }
}
