package test.service;

import main.entity.Author;
import main.repository.AuthorRepository;
import main.service.AuthorService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AuthorServiceTest {

    private AuthorService service;
    private AuthorRepository repository;

    @Before
    public void setUp() {
        repository = new AuthorRepository();
        service = new AuthorService(repository);
    }

    @Test
    public void testFindAll() {
        List<Author> authors = service.findAll();
        assertNotNull(authors);
        assertFalse(authors.isEmpty());
        // Assuming the repository has at least two authors with ids 1 and 2
        assertEquals(2, authors.size());
        assertEquals(1L, (long) authors.get(0).getId());
        assertEquals(2L, (long) authors.get(1).getId());
    }

    @Test
    public void testFindById() {
        // Assuming the repository has an author with id 1 and name "John Doe"
        Author author = service.findById(1L);
        assertNotNull(author);
        assertEquals(1L, (long) author.getId());
        assertEquals("John Doe", author.getName());
    }

    @Test
    public void testFindByIdNotFound() {
        // Assuming the repository has no author with id 999
        Author author = service.findById(999L);
        assertNull(author);
    }

    @Test
    public void testDelete() {
        // Assuming the repository has an author with id 1 and name "John Doe"
        boolean deleted = service.delete(1L);
        assertTrue(deleted);
        // Verify that the author is no longer in the repository
        Author author = service.findById(1L);
        assertNull(author);
    }

    @Test
    public void testDeleteNotFound() {
        // Assuming the repository has no author with id 999
        boolean deleted = service.delete(999L);
        assertFalse(deleted);
    }

    @Test
    public void testCreate() {
        // Create a new author object with id 3 and name "Jane Smith"
        Author author = new Author(3L, "Jane Smith");
        // Create the author in the service
        Author created = service.create(author);
        assertNotNull(created);
        assertEquals(author.getId(), created.getId());
        assertEquals(author.getName(), created.getName());
    }

    @Test
    public void testUpdate() {
        // Assuming the repository has an author with id 2 and name "Mary Jones"
        // Create a new author object with id 2 and name "Mary Jane"
        Author author = new Author(2L, "Mary Jane");
        // Update the author in the service
        Author updated = service.update(author);
        assertNotNull(updated);
        assertEquals(author.getId(), updated.getId());
        assertEquals(author.getName(), updated.getName());
    }
}
