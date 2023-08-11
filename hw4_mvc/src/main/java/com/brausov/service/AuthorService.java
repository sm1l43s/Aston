package com.brausov.service;

import com.brausov.entity.Author;
import com.brausov.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A service class that implements the {@link BaseService} interface for {@link Author} entities.
 * <p>
 * It provides methods for finding, deleting, creating, and updating authors in a {@link AuthorRepository}.
 */
@Service
public class AuthorService implements BaseService<Author, Long> {

    // A field that holds a reference to an AuthorRepository
    private final AuthorRepository authorRepository;

    /**
     * A constructor that takes an AuthorRepository object as a parameter and assigns it to the field.
     *
     * @param authorRepository an object of AuthorRepository class
     */
    public AuthorService() {
        this.authorRepository = new AuthorRepository();
    }

    /**
     * A method that returns a list of all authors in the repository.
     *
     * @return a list of Author objects
     */
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    /**
     * A method that returns an author by its id in the repository.
     *
     * @param id a Long value representing the id of the author
     * @return an Author object or null if not found
     */
    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id);
    }

    public Author findByName(String name) {
        return authorRepository.findByName(name);
    }
    /**
     * A method that deletes an author by its id in the repository.
     *
     * @param id a Long value representing the id of the author
     * @return true if the author was deleted, false otherwise
     */
    @Override
    public boolean delete(Long id) {
        return authorRepository.delete(id);
    }

    /**
     * A method that creates a new author in the repository.
     *
     * @param entity an Author object to be created
     * @return the created Author object or null if failed
     */
    @Override
    public Author create(Author entity) {
        return authorRepository.create(entity);
    }

    /**
     * A method that updates an existing author in the repository.
     *
     * @param entity an Author object to be updated
     * @return the updated Author object or null if failed
     */
    @Override
    public Author update(Author entity) {
        return authorRepository.update(entity);
    }
}
