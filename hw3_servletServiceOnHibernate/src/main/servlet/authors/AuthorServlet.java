package main.servlet.authors;

import main.entity.Author;
import main.service.AuthorService;
import main.service.BaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * A servlet that handles displaying and deleting authors in the database.
 * It allows the user to view the list of all authors and their ids and names.
 * It also provides links to add, edit, or delete an author.
 */
@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {

    private BaseService authorService;

    public AuthorServlet() {
        this.authorService = new AuthorService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long m = System.currentTimeMillis();
        List<Author> authors = authorService.findAll();


        resp.setContentType("text/html;");
        PrintWriter writer = resp.getWriter();

        writer.println("<html><head><title>Servlet</title><style>td {border: 1px solid black; padding: 10px;} table tr:nth-child(1) {background: black; font-weight: bold; color: white;}");
        writer.println("</style></head><body>");

        writer.println("<h2>Authors info</h2>");
        writer.println("<div><a href='/addAuthors'>Add author</a></div>");
        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<td>Id</td>");
        writer.println("<td>Name</td>");
        writer.println("<td>Action</td>");
        writer.println("</tr>");

        for (Author author : authors) {
            writer.println("<tr>");
            writer.println("<td>" + author.getId() + "</td>");
            writer.println("<td>" + author.getName() + "</td>");
            writer.println("<td><form method='post' action='/deleteAuthors'>");
            writer.println("<input type='hidden' name='idAuthor' value='" + author.getId() + "'>");
            writer.println("<input type='submit' value='Delete'>");
            writer.println("</form>");
            writer.println("<a href='/editAuthor?idAuthor=" + author.getId() + "'>Edit</a></td>");
            writer.println("</tr>");
        }

        writer.println("</table>");
        writer.println("<div><a href='/'>Back to main page</a></div>");
        writer.close();

        System.out.println((double) (System.currentTimeMillis() - m));
    }
}
