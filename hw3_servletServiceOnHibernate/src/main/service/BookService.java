package main.service;

import main.entity.Book;
import main.repository.BookRepository;

import java.util.List;

/**
 * A service class that implements the BaseService interface for Book entities.
 * It provides methods for finding, deleting, creating, and updating books in a repository.
 */
public class BookService implements BaseService<Book, Long> {

    // A field that holds a reference to the BooksRepository object
    private final BookRepository booksRepository;

    /**
     * A constructor that takes a BooksRepository object as a parameter and assigns it to the field.
     *
     * @param booksRepository the BooksRepository object to be used by this service
     */
    public BookService() {
        this.booksRepository = new BookRepository();
    }

    /**
     * A method that returns a list of all books in the repository.
     *
     * @return a list of Book objects
     */
    @Override
    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    /**
     * A method that returns a book with the given id from the repository.
     *
     * @param id the id of the book to be returned
     * @return a Book object or null if not found
     */
    @Override
    public Book findById(Long id) {
        return booksRepository.findById(id);
    }

    /**
     * A method that deletes a book with the given id from the repository.
     *
     * @param id the id of the book to be deleted
     * @return true if the book was deleted, false otherwise
     */
    @Override
    public boolean delete(Long id) {
        return booksRepository.delete(id);
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
