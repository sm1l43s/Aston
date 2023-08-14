package com.brausov.rest;

import com.brausov.entity.Author;
import com.brausov.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class RestAuthorController {

    private final BaseService authorService;

    public RestAuthorController(BaseService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAll() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable("id") long id) {
        return (Author) authorService.findById(id);
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        return (Author) authorService.create(author);
    }

    @PutMapping
    public Author editAuthor(@RequestBody Author author) {
        return (Author) authorService.update(author);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody Author author) {
        authorService.deleteById(author.getId());
    }
}
