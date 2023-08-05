package main.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A servlet that handles requests to the root URL and displays the main page with links to authors and books pages.
 */
@WebServlet(name = "main", urlPatterns = "/")
public class MainServlet extends HttpServlet {

    /**
     * Processes GET requests and writes the HTML content of the main page to the response.
     *
     * @param req  the HttpServletRequest object that contains the request information
     * @param resp the HttpServletResponse object that contains the response information
     * @throws ServletException if an error occurs while handling the request
     * @throws IOException      if an error occurs while writing to the response
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;");
        PrintWriter writer = resp.getWriter();

        writer.println("<html><head><title> Servlet </title>");
        writer.println("</style></head><body>");

        writer.println("<h1> Main page </h1>");
        writer.println("<a href='/authors'> authors </a>");
        writer.println("<a href='/books'> books </a>");
    }
}
