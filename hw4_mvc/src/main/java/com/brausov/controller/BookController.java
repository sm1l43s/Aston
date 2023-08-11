package com.brausov.controller;

import com.brausov.entity.Book;
import com.brausov.service.AuthorService;
import com.brausov.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BaseService bookService;
    private final AuthorService authorService;

    public BookController(BaseService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public ModelAndView getAllBooks() {
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject("books", bookService.findAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") Book book) {
        book.setAuthor(authorService.findById(Long.valueOf(book.getAuthor().getName())));
        bookService.update(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getEditBookPage(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("editBook");
        modelAndView.addObject("book", bookService.findById(id));
        modelAndView.addObject("authors", authorService.findAll());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView getAddBookPage() {
        ModelAndView modelAndView = new ModelAndView("addBook");
        modelAndView.addObject("authors", authorService.findAll());
        return modelAndView;
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        book.setAuthor(authorService.findById(Long.valueOf(book.getAuthor().getName())));
        bookService.create(book);
        return "redirect:/books";
    }

    @GetMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
