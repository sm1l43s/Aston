package main.servlet.authors;

import main.repository.AuthorRepository;
import main.service.AuthorService;
import main.service.BaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteAuthors")
public class AuthorDeleteServlet extends HttpServlet {

    private BaseService authorService;

    public AuthorDeleteServlet() {
        this.authorService = new AuthorService(new AuthorRepository());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idAuthor = Long.parseLong(req.getParameter("idAuthor"));
        authorService.delete(idAuthor);

        resp.sendRedirect(req.getContextPath() + "/authors");
    }
}
