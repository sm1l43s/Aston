package com.brausov.controller;

import com.brausov.entity.Book;
import com.brausov.entity.Reader;
import com.brausov.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/readers")
public class ReaderController {

    private final BaseService readerService;
    private final BaseService bookService;

    public ReaderController(BaseService readerService, BaseService bookService) {
        this.readerService = readerService;
        this.bookService = bookService;
    }

    @GetMapping
    public ModelAndView getAllReaders() {
        ModelAndView modelAndView = new ModelAndView("readers/readers");
        modelAndView.addObject("readers", readerService.findAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editReader(@ModelAttribute("reader") Reader reader) {
        readerService.update(reader);
        return "redirect:/readers";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getEditReaderPage(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("readers/editReader");
        modelAndView.addObject("reader", readerService.findById(id));
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView getAddReaderPage() {
        ModelAndView modelAndView = new ModelAndView("readers/addReader");
        return modelAndView;
    }

    @PostMapping("/add")
    public String addReader(@ModelAttribute("reader") Reader reader) {
        readerService.create(reader);
        return "redirect:/readers";
    }

    @GetMapping("/{id}/addBook")
    public ModelAndView getAddBookForReaderPage(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("readers/addBookRead");
        modelAndView.addObject("books", bookService.findAll());
        modelAndView.addObject("readerId", id);
        return modelAndView;
    }

    @PostMapping("/{id}/addBook")
    public String dddBookForReader(@PathVariable("id") long id,
                                   @ModelAttribute("book") Book book) {
        Reader reader = (Reader) readerService.findById(id);
        Book book1 = (Book) bookService.findById(book.getId());

        List<Book> books = reader.getBooks();
        books.add(book1);
        reader.setBooks(books);

        readerService.update(reader);
        return "redirect:/readers";
    }

    @GetMapping("/remove/{id}")
    public String removeReaders(@PathVariable("id") long id) {
        readerService.deleteById(id);
        return "redirect:/readers";
    }

}
