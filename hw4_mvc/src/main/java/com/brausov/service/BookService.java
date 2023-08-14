package com.brausov.service;

import com.brausov.entity.Book;
import com.brausov.exception.ResourceNotFoundException;
import com.brausov.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A service class that implements the BaseService interface for Book entities.
 * It provides methods for finding, deleting, creating, and updating books in a repository.
 */
@Service
public class BookService implements BaseService<Book, Long> {

    // A field that holds a reference to the BooksRepository object
    private final BookRepository booksRepository;

    /**
     * A constructor that takes a BooksRepository object as a parameter and assigns it to the field.
     *
     * @param booksRepository the BooksRepository object to be used by this service
     */
    public BookService(BookRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    /**
     * A method that returns a list of all books in the repository.
     *
     * @return a list of Book objects
     */
    @Override
    public List<Book> findAll() {
        return (List<Book>) booksRepository.findAll();
    }

    /**
     * A method that returns a book with the given id from the repository.
     *
     * @param id the id of the book to be returned
     * @return a Book object or null if not found
     */
    @Override
    public Book findById(Long id) {
        Book book = booksRepository.findById(id);
        if (book != null) {
            return book;
        }
        throw new ResourceNotFoundException("Book with id: " + id + " not found");
    }

    /**
     * A method that deletes a book with the given id from the repository.
     *
     * @param id the id of the book to be deleted
     * @return true if the book was deleted, false otherwise
     */
    @Override
    public void deleteById(Long id) {
        Book book = booksRepository.findById(id);
        if (book != null) {
            booksRepository.delete(id);
        }
        throw new ResourceNotFoundException("Book with id: " + id + " not found");
    }

    /**
     * A method that creates a new book in the repository.
     *
     * @param entity the Book object to be created
     * @return the created Book object
     */
    @Override
    public Book create(Book entity) {
        return booksRepository.create(entity);
    }

    /**
     * A method that updates an existing book in the repository.
     *
     * @param entity the Book object to be updated
     * @return the updated Book object
     */
    @Override
    public Book update(Book entity) {
        return booksRepository.update(entity);
    }
}
