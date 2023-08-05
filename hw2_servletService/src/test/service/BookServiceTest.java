package test.service;

import main.entity.Author;
import main.entity.Book;
import main.repository.BooksRepository;
import main.service.BookService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService service;
    private BooksRepository repository;

    @Before
    public void setUp() {
        repository = new BooksRepository();
        service = new BookService(repository);
    }

    @Test
    public void testFindAll() {
        List<Book> books = service.findAll();
        assertNotNull(books);
        assertFalse(books.isEmpty());
        // Assuming the repository has at least two books with ids 1 and 2
        assertEquals(2, books.size());
        assertEquals(1L, (long) books.get(0).getId());
        assertEquals(2L, (long) books.get(1).getId());
    }

    @Test
    public void testFindById() {
        // Assuming the repository has a book with id 1, title "The Catcher in the Rye", isbn "978-0140237504", and author_id 1
        Book book = service.findById(1L);
        assertNotNull(book);
        assertEquals(1L, (long) book.getId());
        assertEquals("The Catcher in the Rye", book.getTitle());
        assertEquals("978-0140237504", book.getIsbn());
        assertEquals(1L, (long) book.getAuthor().getId());
    }

    @Test
    public void testFindByIdNotFound() {
        // Assuming the repository has no book with id 999
        Book book = service.findById(999L);
        assertNull(book);
    }

    @Test
    public void testDelete() {
        // Assuming the repository has a book with id 1, title "The Catcher in the Rye", isbn "978-0140237504", and author_id 1
        boolean deleted = service.delete(1L);
        assertTrue(deleted);
        // Verify that the book is no longer in the repository
        Book book = service.findById(1L);
        assertNull(book);
    }

    @Test
    public void testDeleteNotFound() {
        // Assuming the repository has no book with id 999
        boolean deleted = service.delete(999L);
        assertFalse(deleted);
    }

    @Test
    public void testCreate() {
        // Create a new book object with id 3, title "1984", isbn "978-0451524935", and author_id 2
        Book book = new Book(3L, "1984", "978-0451524935", new Author(2L, "George Orwell"));
        // Create the book in the service
        Book created = service.create(book);
        assertNotNull(created);
        assertEquals(book.getId(), created.getId());
        assertEquals(book.getTitle(), created.getTitle());
        assertEquals(book.getIsbn(), created.getIsbn());
        assertEquals(book.getAuthor().getId(), created.getAuthor().getId());
    }

    @Test
    public void testUpdate() {
        // Assuming the repository has a book with id 2, title "To Kill a Mockingbird", isbn "978-0446310789", and author_id 3
        // Create a new book object with id 2, title "To Kill a Mockingbird (50th Anniversary Edition)", isbn "978-0061743528", and author_id 3
        Book book = new Book(2L, "To Kill a Mockingbird (50th Anniversary Edition)", "978-0061743528", new Author(3L, "Harper Lee"));
        // Update the book in the service
        Book updated = service.update(book);
        assertNotNull(updated);
        assertEquals(book.getId(), updated.getId());
        assertEquals(book.getTitle(), updated.getTitle());
        assertEquals(book.getIsbn(), updated.getIsbn());
        assertEquals(book.getAuthor().getId(), updated.getAuthor().getId());
    }
}

