package test.repository;

import main.entity.Author;
import main.repository.AuthorRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AuthorRepositoryTest {

    private AuthorRepository repository;

    @Before
    public void setUp() {
        repository = new AuthorRepository();
    }

    @After
    public void tearDown() {
        repository = null;
    }

    @Test
    public void testFindAll() {
        List<Author> authors = repository.findAll();
        assertNotNull(authors);
        assertFalse(authors.isEmpty());
        // Assuming the database has at least two authors with ids 1 and 2
        assertEquals(2, authors.size());
        assertEquals( 1L, (long) authors.get(0).getId());
        assertEquals(1L, (long) authors.get(1).getId());
    }

    @Test
    public void testFindById() {
        // Assuming the database has an author with id 1 and name "John Doe"
        Author author = repository.findById(1L);
        assertNotNull(author);
        assertEquals(1L, (long) author.getId());
        assertEquals("John Doe", author.getName());
    }

    @Test
    public void testFindByIdNotFound() {
        // Assuming the database has no author with id 999
        Author author = repository.findById(999L);
        assertNull(author);
    }

    @Test
    public void testDelete() {
        // Assuming the database has an author with id 1 and name "John Doe"
        boolean deleted = repository.delete(1L);
        assertTrue(deleted);
        // Verify that the author is no longer in the database
        Author author = repository.findById(1L);
        assertNull(author);
    }

    @Test
    public void testDeleteNotFound() {
        // Assuming the database has no author with id 999
        boolean deleted = repository.delete(999L);
        assertFalse(deleted);
    }

    @Test
    public void testInsert() {
        // Create a new author object with id 3 and name "Jane Smith"
        Author author = new Author(3L, "Jane Smith");
        // Insert the author into the database
        repository.create(author);
        // Verify that the author is in the database
        Author found = repository.findById(3L);
        assertNotNull(found);
        assertEquals(author.getId(), found.getId());
        assertEquals(author.getName(), found.getName());
    }

    @Test
    public void testUpdate() {
        // Assuming the database has an author with id 2 and name "Mary Jones"
        // Create a new author object with id 2 and name "Mary Jane"
        Author author = new Author(2L, "Mary Jane");
        // Update the author in the database
        repository.update(author);
        // Verify that the author is updated in the database
        Author found = repository.findById(2L);
        assertNotNull(found);
        assertEquals(author.getId(), found.getId());
        assertEquals(author.getName(), found.getName());
    }
}
