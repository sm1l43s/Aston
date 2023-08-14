package main.servlet;

import main.entity.Author;
import main.repository.AuthorRepository;
import main.repository.BaseRepository;
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

@WebServlet(name = "main", urlPatterns = {"/"})
public class MainServlet extends HttpServlet {

    BaseService authorService;
    public MainServlet() {
            authorService = new AuthorService();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title> Servlet </title>");
        writer.println("</style></head><body>");
        writer.println("<h1> Main page with Hibernate </h1>");
        writer.println("<a href='/authors'> authors </a>");
        writer.println("<a href='/books'> books </a>");
    }
}

