package main.servlet.authors;

import main.service.AuthorService;
import main.service.BaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A servlet that handles requests to delete an author by its id from the database and redirects to the authors page.
 */
@WebServlet("/deleteAuthors")
public class AuthorDeleteServlet extends HttpServlet {

    private BaseService authorService;

    /**
     * Constructs a new AuthorDeleteServlet with an AuthorService instance that uses an AuthorRepository.
     */
    public AuthorDeleteServlet() {
        this.authorService = new AuthorService();
    }

    /**
     * Processes POST requests and deletes the author with the given id from the database.
     *
     * @param req  the HttpServletRequest object that contains the request information
     * @param resp the HttpServletResponse object that contains the response information
     * @throws ServletException if an error occurs while handling the request
     * @throws IOException      if an error occurs while writing to the response or redirecting
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idAuthor = Long.parseLong(req.getParameter("idAuthor"));
        authorService.delete(idAuthor);

        resp.sendRedirect(req.getContextPath() + "/authors");
    }
}
