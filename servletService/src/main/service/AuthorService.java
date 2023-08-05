package main.service;

import main.entity.Author;
import main.repository.AuthorRepository;

import java.util.List;

public class AuthorService implements BaseService<Author, Long>{

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean delete(Long id) {
        return authorRepository.delete(id);
    }

    @Override
    public Author create(Author entity) {
        return authorRepository.create(entity);
    }

    @Override
    public Author update(Author entity) {
        return authorRepository.update(entity);
    }
}
