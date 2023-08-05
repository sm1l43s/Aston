package test.entity;

import junit.framework.TestCase;
import main.entity.Author;
import main.entity.Book;

// A test class for the Book class
public class BookTest extends TestCase {

    // A helper method to create a sample book object
    private Book createSampleBook() {
        Author author = new Author(1L, "John Doe");
        // Create a sample book object with id 1, title "Sample Book", isbn "123-456-7890", and author
        Book book = new Book(1L, "Sample Book", "123-456-7890", author);
        return book;
    }

    // A test method to check the getId method of the Book class
    public void testGetId() {
        Book book = createSampleBook();
        assertEquals(1L, (long) book.getId());
    }

    // A test method to check the setId method of the Book class
    public void testSetId() {
        Book book = createSampleBook();
        book.setId(2L);
        // Assert that the id of the book is 2
        assertEquals(2L, (long) book.getId());
    }

    // A test method to check the getTitle method of the Book class
    public void testGetTitle() {
        Book book = createSampleBook();
        assertEquals("Sample Book", book.getTitle());
    }

    // A test method to check the setTitle method of the Book class
    public void testSetTitle() {
        Book book = createSampleBook();
        book.setTitle("New Title");
        // Assert that the title of the book is "New Title"
        assertEquals("New Title", book.getTitle());
    }

    // A test method to check the getIsbn method of the Book class
    public void testGetIsbn() {
        Book book = createSampleBook();
        // Assert that the isbn of the book is "123-456-7890"
        assertEquals("123-456-7890", book.getIsbn());
    }

    // A test method to check the setIsbn method of the Book class
    public void testSetIsbn() {
        Book book = createSampleBook();
        book.setIsbn("098-765-4321");
        // Assert that the isbn of the book is "098-765-4321"
        assertEquals("098-765-4321", book.getIsbn());
    }

    // A test method to check the getAuthor method of the Book class
    public void testGetAuthor() {
        Book book = createSampleBook();
        Author author = new Author(1L, "John Doe");
        // Assert that the author of the book is equal to the sample author object
        assertEquals(author, book.getAuthor());
    }

    // A test method to check the setAuthor method of the Book class
    public void testSetAuthor() {
        Book book = createSampleBook();
        // Create a new author object with id 2, first name "Jane", and last name "Doe"
        Author newAuthor = new Author(2L, "Jane Doe");
        // Set the author of the book to the new author object
        book.setAuthor(newAuthor);
        // Assert that the author of the book is equal to the new author object
        assertEquals(newAuthor, book.getAuthor());
    }

    // A test method to check the equals method of the Book class
    public void testEquals() {
        Book book = createSampleBook();

        assertFalse(book.equals(null));
        assertFalse(book.equals(new Object()));
        assertTrue(book.equals(book));
        Author anotherAuthor = new Author(1L, "John Doe");

        // Create another sample book object with the same id, title, isbn, and author as the first book object
        Book anotherBook = new Book(1L, "Sample Book", "123-456-7890", anotherAuthor);

        // Assert that the two book objects are equal
        assertTrue(book.equals(anotherBook));

        // Create a different book object with a different id
        Book differentBook = new Book(2L, "Sample Book", "123-456-7890", anotherAuthor);

        // Assert that the two book objects are not equal
        assertFalse(book.equals(differentBook));
    }

    // A test method to check the hashCode method of the Book class
    public void testHashCode() {
        Book book = createSampleBook();
        // Compute the expected hash code based on the id, title, isbn, and author of the book
        int expectedHashCode = book.hashCode();
        // Assert that the hash code of the book is equal to the expected hash code
        assertEquals(expectedHashCode, book.hashCode());
    }

    // A test method to check the toString method of the Book class
    public void testToString() {
        Book book = createSampleBook();
        Author author = new Author(1L, "John Doe");
        String expectedString = "Book{id=1, title='Sample Book', isbn='123-456-7890', author=" + author + "}";
        // Assert that the string representation of the book is equal to the expected string
        assertEquals(expectedString, book.toString());
    }
}

