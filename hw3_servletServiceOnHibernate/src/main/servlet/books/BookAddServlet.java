package main.servlet.books;

import main.entity.Author;
import main.entity.Book;
import main.service.AuthorService;
import main.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * A servlet that handles adding a new book to the database.
 * It allows the user to enter the id, title, ISBN, and author of the book and submit the data.
 * It also displays a dropdown list of all authors to choose from and redirects the user to the list of all books after adding the new one.
 */
@WebServlet("/addBooks")
public class BookAddServlet extends HttpServlet {

    private final BookService bookService;

    private final AuthorService authorService;

    public BookAddServlet() {
        this.bookService = new BookService();
        this.authorService = new AuthorService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorService.findAll();

        resp.setContentType("text/html; charset=windows-1251");
        PrintWriter writer = resp.getWriter();

        writer.println("<html><head><title>Servlet</title><style>td {border: 1px solid black; padding: 10px;} table tr:nth-child(1) {background: black; font-weight: bold; color: white;}");
        writer.println("</style></head><body>");

        writer.println("<form method='post' action='/addBooks'>");
        writer.println("<input type='text' name='title' placeholder='title'>");
        writer.println("<input type='text' name='isbn' placeholder='isbn'>");
        writer.println("<select name='idAuthor' id='idAuthor'>");

        for (Author author : authors) {
            writer.println("<option value=" + author.getId() + ">" + author.getName() + "</option>");
        }
        writer.println("</select>");

        writer.println("<input type='submit' value='Add'>");
        writer.println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String isbn = req.getParameter("isbn");
        Long idAuthor = Long.parseLong(req.getParameter("idAuthor"));

        bookService.create(new Book(title, isbn, authorService.findById(idAuthor)));
        resp.sendRedirect(req.getContextPath() + "/books");
    }
}
