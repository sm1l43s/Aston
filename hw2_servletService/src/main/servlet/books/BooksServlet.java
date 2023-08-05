package main.servlet.books;

import main.entity.Book;
import main.repository.BooksRepository;
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
 * A servlet that handles displaying and deleting books in the database.
 * It allows the user to view the list of all books and their ids, titles, ISBNs, and authors.
 * It also provides links to add, edit, or delete a book.
 */
@WebServlet("/books")
public class BooksServlet extends HttpServlet {

    private final BookService bookService;

    public BooksServlet() {
        this.bookService = new BookService(new BooksRepository());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.findAll();


        resp.setContentType("text/html; charset=windows-1251");
        PrintWriter writer = resp.getWriter();

        writer.println("<html><head><title>Servlet</title><style>td {border: 1px solid black; padding: 10px;} table tr:nth-child(1) {background: black; font-weight: bold; color: white;}");
        writer.println("</style></head><body>");

        writer.println("<h2>Books info</h2>");
        writer.println("<div><a href='/addBooks'>Add books</a></div>");
        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<td>Id</td>");
        writer.println("<td>Name</td>");
        writer.println("<td>ISBN</td>");
        writer.println("<td>Author</td>");
        writer.println("<td>Action</td>");
        writer.println("</tr>");

        for (Book book: books) {
            writer.println("<tr>");
            writer.println("<td>" + book.getId() + "</td>");
            writer.println("<td>" + book.getTitle() + "</td>");
            writer.println("<td>" + book.getIsbn() + "</td>");
            writer.println("<td>" + book.getAuthor().getName() + "</td>");
            writer.println("<td><form method='post' action='/deleteBooks'>");
            writer.println("<input type='hidden' name='idBook' value='" + book.getId() + "'>");
            writer.println("<input type='submit' value='Delete'>");
            writer.println("</form>");
            writer.println("<a href='/editBooks?idBook="+ book.getId() +"'>Edit</a></td>");
            writer.println("</tr>");
        }

        writer.println("</table>");
        writer.println("<div><a href='/'>Back to main page</a></div>");
        writer.close();

    }
}
