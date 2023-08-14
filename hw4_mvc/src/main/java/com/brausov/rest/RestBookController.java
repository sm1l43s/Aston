package com.brausov.rest;

import com.brausov.entity.Book;
import com.brausov.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class RestBookController {


    private final BookService booksService;

    public RestBookController(BookService booksService) {
        this.booksService = booksService;
    }

    @GetMapping
    public List<Book> getAll() {
        return booksService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") long id) {
        return booksService.findById(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return booksService.create(book);
    }

    @PutMapping
    public Book editBook(@RequestBody Book book) {
        return booksService.update(book);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody Book book) {
        booksService.deleteById(book.getId());
    }

}
