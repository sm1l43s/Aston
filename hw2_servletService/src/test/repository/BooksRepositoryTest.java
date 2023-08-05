package test.repository;


import main.entity.Author;
import main.entity.Book;
import main.repository.BooksRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BooksRepositoryTest {

    private BooksRepository repository;

    @Before
    public void setUp() {
        repository = new BooksRepository();
    }

    @After
    public void tearDown() {
        repository = null;
    }

    @Test
    public void testFindAll() {
        List<Book> books = repository.findAll();
        assertNotNull(books);
        assertFalse(books.isEmpty());
        // Assuming the database has at least two books with ids 1 and 2
        assertEquals(2, books.size());
        assertEquals(1L, (long) books.get(0).getId());
        assertEquals(2L, (long) books.get(1).getId());
    }

    @Test
    public void testFindById() {
        // Assuming the database has a book with id 1, title "The Catcher in the Rye", isbn "978-0140237504", and author_id 1
        Book book = repository.findById(1L);
        assertNotNull(book);
        assertEquals(1L, (long) book.getId());
        assertEquals("The Catcher in the Rye", book.getTitle());
        assertEquals("978-0140237504", book.getIsbn());
        assertEquals(1L, (long) book.getAuthor().getId());
    }

    @Test
    public void testFindByIdNotFound() {
        // Assuming the database has no book with id 999
        Book book = repository.findById(999L);
        assertNull(book);
    }

    @Test
    public void testDelete() {
        // Assuming the database has a book with id 1, title "The Catcher in the Rye", isbn "978-0140237504", and author_id 1
        boolean deleted = repository.delete(1L);
        assertTrue(deleted);
        // Verify that the book is no longer in the database
        Book book = repository.findById(1L);
        assertNull(book);
    }

    @Test
    public void testDeleteNotFound() {
        // Assuming the database has no book with id 999
        boolean deleted = repository.delete(999L);
        assertTrue(deleted);
    }

    @Test
    public void testInsert() {
        // Create a new book object with id 3, title "1984", isbn "978-0451524935", and author_id 2
        Book book = new Book(3L, "1984", "978-0451524935", new Author(2L, "George Orwell"));
        // Insert the book into the database
        repository.create(book);
        // Verify that the book is in the database
        Book found = repository.findById(3L);
        assertNotNull(found);
        assertEquals(book.getId(), found.getId());
        assertEquals(book.getTitle(), found.getTitle());
        assertEquals(book.getIsbn(), found.getIsbn());
        assertEquals(book.getAuthor().getId(), found.getAuthor().getId());
    }

    @Test
    public void testUpdate() {
        // Assuming the database has a book with id 2, title "To Kill a Mockingbird", isbn "978-0446310789", and author_id 3
        // Create a new book object with id 2, title "To Kill a Mockingbird (50th Anniversary Edition)", isbn "978-0061743528", and author_id 3
        Book book = new Book(2L, "To Kill a Mockingbird (50th Anniversary Edition)", "978-0061743528", new Author(3L, "Harper Lee"));
        // Update the book in the database
        repository.update(book);
        // Verify that the book is updated in the database
        Book found = repository.findById(2L);
        assertNotNull(found);
        assertEquals(book.getId(), found.getId());
        assertEquals(book.getTitle(), found.getTitle());
        assertEquals(book.getIsbn(), found.getIsbn());
        assertEquals(book.getAuthor().getId(), found.getAuthor().getId());
    }
}
