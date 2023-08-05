package main.repository;

import main.dbConnection.ConnectionPool;
import main.entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A repository class for accessing and manipulating author data in the database.
 * Implements the BaseRepository interface with Author as the entity type and Long as the primary key type.
 */
public class AuthorRepository implements BaseRepository<Author, Long> {

    // A constant string for the SQL query to select all authors from the AUTHORS table
    private static final String SQL_SELECT_ALL_AUTHORS = "SELECT * FROM AUTHORS";
    // A constant string for the SQL query to select an author by id from the AUTHORS table
    private static final String SQL_SELECT_AUTHORS_BY_ID = "SELECT * FROM AUTHORS WHERE id = ?";
    // A constant string for the SQL query to insert a new author into the AUTHORS table
    private static final String SQL_INSERT_AUTHORS = "INSERT INTO AUTHORS(id, name) VALUES (?, ?)";
    // A constant string for the SQL query to delete an author by id from the AUTHORS table
    private static final String SQL_DELETE_AUTHORS = "DELETE FROM AUTHORS WHERE id = ?";
    // A constant string for the SQL query to update an author's name by id in the AUTHORS table
    private static final String SQL_UPDATE_AUTHORS = "UPDATE AUTHORS SET name = ? WHERE id = ?";
    // A field for the connection pool instance
    private final ConnectionPool connectionPool;

    /**
     * A constructor that initializes the connection pool field with the singleton instance of ConnectionPool.
     */
    public AuthorRepository() {
        this.connectionPool = ConnectionPool.getInstance();
    }

    /**
     * A method that returns a list of all authors in the database.
     * @return a list of Author objects, or an empty list if no authors are found.
     */
    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        String sql = "SELECT * FROM AUTHORS";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                authors.add(new Author(id, name));
            }
        } catch (SQLException e) {
            Logger.getLogger(AuthorRepository.class.getName()).log(Level.SEVERE, "Error finding all authors", e);
            throw new RuntimeException(e);
        }
        return authors;
    }

    /**
     * A method that returns an author by id from the database.
     * @param id the primary key of the author to be retrieved.
     * @return an Author object, or null if no author is found with the given id.
     */
    @Override
    public Author findById(Long id) {
        Author author = null;
        String sql = "SELECT * FROM AUTHORS WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    long idAuthor = rs.getLong("id");
                    String name = rs.getString("name");
                    author = new Author(idAuthor, name);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(AuthorRepository.class.getName()).log(Level.SEVERE, "Error finding author by id:" + id, e);
            throw new RuntimeException(e);
        }

        return author;
    }

    /**
     * A method that deletes an author by id from the database.
     * @param id the primary key of the author to be deleted.
     * @return true if the deletion is successful, false otherwise.
     */
    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM AUTHORS WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);

            statement.execute();

            return true;
        } catch (SQLException e) {
            Logger.getLogger(AuthorRepository.class.getName()).log(Level.SEVERE, "Error deleting author by id:" + id, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * A method that creates a new author in the database.
     * @param entity the Author object to be inserted into the database.
     * @return the same Author object, or null if the insertion fails.
     */
    @Override
    public Author create(Author entity) {
        String sql = "INSERT INTO AUTHORS(id, name) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getName());

            statement.execute();

            return entity;
        } catch (SQLException e) {
            Logger.getLogger(AuthorRepository.class.getName()).log(Level.SEVERE, "Error creating author: " + entity, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * A method that updates an existing author in the database.
     * @param entity the Author object with the updated data.
     * @return the same Author object, or null if the update fails.
     */
    @Override
    public Author update(Author entity) {
        String sql = "UPDATE AUTHORS SET name = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getName());
            statement.setLong(2, entity.getId());

            statement.executeUpdate();

            return entity;
        } catch (SQLException e) {
            Logger.getLogger(AuthorRepository.class.getName()).log(Level.SEVERE, "Error updating author: " + entity, e);
            throw new RuntimeException(e);
        }
    }

    // A private helper method that gets a connection from the connection pool
    private Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }
}

