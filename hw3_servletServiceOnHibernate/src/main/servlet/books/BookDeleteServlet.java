package main.servlet.books;

import main.service.BaseService;
import main.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A servlet that handles deleting a book from the database.
 * It allows the user to submit the id of the book to be deleted and redirects the user to the list of all books after deletion.
 */
@WebServlet("/deleteBooks")
public class BookDeleteServlet extends HttpServlet {

    private BaseService bookService;

    public BookDeleteServlet() {
        this.bookService = new BookService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idBook = Long.parseLong(req.getParameter("idBook"));
        bookService.delete(idBook);

        resp.sendRedirect(req.getContextPath() + "/books");
    }

}
