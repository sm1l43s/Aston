package main.repository;

import main.dbConnection.ConnectionPool;
import main.entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository implements BaseRepository<Author, Long> {

    private static final String SQL_SELECT_ALL_AUTHORS = "SELECT * FROM AUTHORS";
    private static final String SQL_SELECT_AUTHORS_BY_ID = "SELECT * FROM AUTHORS WHERE id = ?";
    private static final String SQL_INSERT_AUTHORS = "INSERT INTO AUTHORS(id, name) VALUES (?, ?)";
    private static final String SQL_DELETE_AUTHORS = "DELETE FROM AUTHORS WHERE id = ?";
    private static final String SQL_UPDATE_AUTHORS = "UPDATE AUTHORS SET name = ? WHERE id = ?";
    private final ConnectionPool connectionPool;

    public AuthorRepository() {
        this.connectionPool = ConnectionPool.getInstance();
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_AUTHORS)) {

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                authors.add(new Author(id, name));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return authors;
    }

    @Override
    public Author findById(Long id) {
        Author author = null;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_AUTHORS_BY_ID)) {
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                long idAuthor = rs.getLong("id");
                String name = rs.getString("name");
                author = new Author(idAuthor, name);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return author;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_AUTHORS)) {
            statement.setLong(1, id);

            statement.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Author create(Author entity) {

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_AUTHORS)) {
            statement.setLong(1, entity.getId());
            statement.setString(2, entity.getName());

            statement.execute();

            return entity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Author update(Author entity) {

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_AUTHORS)) {
            statement.setString(1, entity.getName());
            statement.setLong(2, entity.getId());

            statement.executeUpdate();
            return entity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
