package main.repository;

import main.dbConnection.ConnectionPool;
import main.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A repository class for accessing and manipulating book data in the database.
 * Implements the BaseRepository interface with Book as the entity type and Long as the primary key type.
 */
public class BooksRepository implements BaseRepository<Book, Long> {

    // A constant string for the SQL query to select all books from the BOOKS table
    private static final String SQL_SELECT_ALL_BOOKS = "SELECT * FROM BOOKS";
    // A constant string for the SQL query to select a book by id from the BOOKS table
    private static final String SQL_SELECT_BOOKS_BY_ID = "SELECT * FROM BOOKS WHERE id = ?";
    // A constant string for the SQL query to insert a new book into the BOOKS table
    private static final String SQL_INSERT_BOOKS = "INSERT INTO BOOKS(id, title, isbn, author_id) VALUES (?, ?, ?, ?)";
    // A constant string for the SQL query to delete a book by id from the BOOKS table
    private static final String SQL_DELETE_BOOKS = "DELETE FROM BOOKS WHERE id = ?";
    // A constant string for the SQL query to update a book's title, isbn, and author_id by id in the BOOKS table
    private static final String SQL_UPDATE_BOOKS = "UPDATE BOOKS SET title = ?, isbn = ?, author_id = ? WHERE id = ?";
    // A field for the connection pool instance
    private final ConnectionPool connectionPool;
    // A field for the author repository instance
    private final AuthorRepository authorRepository;

    /**
     * A constructor that initializes the connection pool and author repository fields with the singleton instances of ConnectionPool and AuthorRepository.
     */
    public BooksRepository() {
        this.authorRepository = new AuthorRepository();
        this.connectionPool = ConnectionPool.getInstance();
    }

    /**
     * A method that returns a list of all books in the database.
     *
     * @return a list of Book objects, or an empty list if no books are found.
     */
    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_BOOKS)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String title = rs.getString("title");
                String isbn = rs.getString("isbn");
                long idAuthor = rs.getLong("author_id");
                books.add(new Book(id, title, isbn, authorRepository.findById(idAuthor)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    /**
     * A method that returns a book by id from the database.
     *
     * @param id the primary key of the book to be retrieved.
     * @return a Book object, or null if no book is found with the given id.
     */
    @Override
    public Book findById(Long id) {
        Book book = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BOOKS_BY_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                long idBook = rs.getLong("id");
                String title = rs.getString("title");
                String isbn = rs.getString("isbn");
                long idAuthor = rs.getLong("author_id");
                book = new Book(idBook, title, isbn, authorRepository.findById(idAuthor));
            }
            return book;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A method that deletes a book by id from the database.
     *
     * @param id the primary key of the book to be deleted.
     * @return true if the deletion is successful, false otherwise.
     */
    @Override
    public boolean delete(Long id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BOOKS)) {
            statement.setLong(1, id);
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A method that creates a new book in the database.
     *
     * @param entity the Book object to be inserted into the database.
     * @return the same Book object, or null if an error occurs.
     */
    @Override
    public Book create(Book entity) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_BOOKS)) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getTitle());
            statement.setString(3, entity.getIsbn());
            statement.setLong(4, entity.getAuthor().getId());
            statement.execute();
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A method that updates an existing book in the database.
     *
     * @param entity the Book object with the updated values.
     * @return the same Book object, or null if an error occurs.
     */
    @Override
    public Book update(Book entity) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BOOKS)) {
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getIsbn());
            statement.setLong(3, entity.getAuthor().getId());
            statement.setLong(4, entity.getId());
            statement.executeUpdate();
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

