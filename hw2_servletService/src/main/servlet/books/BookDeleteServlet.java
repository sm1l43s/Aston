package main.servlet.books;

import main.repository.BooksRepository;
import main.service.BaseService;
import main.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteBooks")
public class BookDeleteServlet extends HttpServlet {

    private BaseService bookService;

    public BookDeleteServlet() {
        this.bookService = new BookService(new BooksRepository());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long idBook = Long.parseLong(req.getParameter("idBook"));
        bookService.delete(idBook);

        resp.sendRedirect(req.getContextPath() + "/books");
    }

}
