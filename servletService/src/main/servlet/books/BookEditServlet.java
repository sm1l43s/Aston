package main.servlet.books;

import main.entity.Author;
import main.entity.Book;
import main.repository.AuthorRepository;
import main.repository.BooksRepository;
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

@WebServlet("/editBooks")
public class BookEditServlet extends HttpServlet {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookEditServlet() {
        this.bookService = new BookService(new BooksRepository());
        this.authorService = new AuthorService(new AuthorRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorService.findAll();
        Long idBook = Long.parseLong(req.getParameter("idBook"));
        Book book = bookService.findById(idBook);

        resp.setContentType("text/html; charset=windows-1251");
        PrintWriter writer = resp.getWriter();

        writer.println("<html><head><title>Servlet</title><style>td {border: 1px solid black; padding: 10px;} table tr:nth-child(1) {background: black; font-weight: bold; color: white;}");
        writer.println("</style></head><body>");

        writer.println("<form method='post' action='/editBooks'>");
        writer.println("<input type='text hidden' name='idBook' value='" + book.getId() + "'>");
        writer.println("<input type='text' name='title' value='" + book.getTitle() + "'>");
        writer.println("<input type='text' name='isbn' value='" + book.getIsbn() + "'>");
        writer.println("<select name='idAuthor' id='idAuthor'>");

        for (Author author: authors) {
            if (book.getAuthor().getId() == author.getId()) {
                writer.println("<option value=" + author.getId() + " selected>" + author.getName() + "</option>");
            } else {
                writer.println("<option value=" + author.getId() + ">" + author.getName() + "</option>");
            }
        }
        writer.println("</select>");

        writer.println("<input type='submit' value='Edit'>");
        writer.println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idBook = Long.parseLong(req.getParameter("idBook"));
        String title = req.getParameter("title");
        String isbn = req.getParameter("isbn");
        Long idAuthor = Long.parseLong(req.getParameter("idAuthor"));

        bookService.update(new Book(idBook, title, isbn, authorService.findById(idAuthor)));
        resp.sendRedirect(req.getContextPath() + "/books");
    }

}
