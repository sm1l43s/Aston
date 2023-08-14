package test.entity;


import junit.framework.TestCase;
import main.entity.Author;

/**
 * A class that tests the Author class.
 */
public class AuthorTest extends TestCase {

    // An author object to use in the tests
    private Author author;

    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    protected void setUp() {
        // Create an author object with id 1 and name "John Smith"
        author = new Author(1L, "John Smith");
    }

    /**
     * Tests the constructor of the Author class.
     */
    public void testConstructor() {
        // Check that the author object is not null
        assertNotNull(author);

        // Check that the id and name of the author are correct
        assertEquals(1L, (long) author.getId());
        assertEquals("John Smith", author.getName());
    }

    /**
     * Tests the setId method of the Author class.
     */
    public void testSetId() {
        // Set a new id for the author
        author.setId(2L);

        // Check that the id of the author is updated
        assertEquals(2L, (long) author.getId());
    }

    /**
     * Tests the setName method of the Author class.
     */
    public void testSetName() {
        // Set a new name for the author
        author.setName("Jane Doe");

        // Check that the name of the author is updated
        assertEquals("Jane Doe", author.getName());
    }

    /**
     * Tests the equals method of the Author class.
     */
    public void testEquals() {
        // Create another author object with the same id and name as the first one
        Author other = new Author(1L, "John Smith");

        // Check that the two authors are equal
        assertTrue(author.equals(other));

        // Change the id and name of the other author
        other.setId(3L);
        other.setName("Alice Cooper");

        // Check that the two authors are not equal
        assertFalse(author.equals(other));
    }

    /**
     * Tests the hashCode method of the Author class.
     */
    public void testHashCode() {
        // Create another author object with the same id and name as the first one
        Author other = new Author(1L, "John Smith");

        // Check that the hash codes of the two authors are equal
        assertEquals(author.hashCode(), other.hashCode());

        // Change the id and name of the other author
        other.setId(3L);
        other.setName("Alice Cooper");

        // Check that the hash codes of the two authors are not equal
        assertNotSame(author.hashCode(), other.hashCode());
    }

    /**
     * Tests the toString method of the Author class.
     */
    public void testToString() {
        // Check that the string representation of the author is correct
        assertEquals("Author{id=1, name='John Smith'}", author.toString());
    }
}

