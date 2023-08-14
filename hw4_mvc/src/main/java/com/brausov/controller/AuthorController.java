package com.brausov.controller;

import com.brausov.entity.Author;
import com.brausov.service.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/authors")
public class AuthorController {

    private final BaseService authorService;

    public AuthorController(BaseService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ModelAndView getAllAuthors(Model model) {
        ModelAndView modelAndView = new ModelAndView("authors/authors");
        modelAndView.addObject("authors", authorService.findAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public String editAuthor(@ModelAttribute("author") Author author) {
        authorService.update(author);
        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getEditAuthorPage(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView("authors/editAuthor");
        modelAndView.addObject("author", authorService.findById(id));
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView getAddAuthorPage() {
        ModelAndView modelAndView = new ModelAndView("authors/addAuthor");
        return modelAndView;
    }

    @PostMapping("/add")
    public String addAuthor(@ModelAttribute("author") Author author) {
        authorService.create(author);
        return "redirect:/authors";
    }
    @GetMapping("/remove/{id}")
    public String removeAuthor(@PathVariable("id") long id) {
        authorService.deleteById(id);
        return "redirect:/authors";
    }
}
