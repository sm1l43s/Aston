package main.servlet.authors;

import main.entity.Author;
import main.repository.AuthorRepository;
import main.service.AuthorService;
import main.service.BaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/editAuthor")
public class EditServlet extends HttpServlet {

    private BaseService authorService;

    public EditServlet() {
        this.authorService = new AuthorService(new AuthorRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idAuthor = Long.parseLong(req.getParameter("idAuthor"));

        Author author = (Author) authorService.findById(idAuthor);

        resp.setContentType("text/html; charset=windows-1251");
        PrintWriter writer = resp.getWriter();

        writer.println("<html><head><title>Servlet</title><style>td {border: 1px solid black; padding: 10px;} table tr:nth-child(1) {background: black; font-weight: bold; color: white;}");
        writer.println("</style></head><body>");

        writer.println("<form method='post' action='/editAuthor'>");
        writer.println("<input type='hidden' name='idAuthor' value='" + author.getId() + "'>");
        writer.println("<input type='text' name='nameAuthor' value='" + author.getName() + "'>");
        writer.println("<input type='submit' value='Edit'>");
        writer.println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idAuthor = Long.parseLong(req.getParameter("idAuthor"));
        String nameAuthor = req.getParameter("nameAuthor");

        authorService.update(new Author(idAuthor, nameAuthor));
        resp.sendRedirect(req.getContextPath() + "/authors");
    }

}
