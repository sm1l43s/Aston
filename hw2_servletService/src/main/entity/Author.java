package main.entity;

import java.util.Objects;

/**
 * A class that represents an author of a book or an article.
 */
public class Author {

    // The unique identifier of the author
    private Long id;

    // The name of the author
    private String name;

    /**
     * Constructs a new Author object with the given id and name.
     *
     * @param id   the unique identifier of the author
     * @param name the name of the author
     */
    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the id of the author.
     *
     * @return the id of the author
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the author.
     *
     * @param id the new id of the author
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the name of the author.
     *
     * @return the name of the author
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the author.
     *
     * @param name the new name of the author
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if this author is equal to another object.
     * <p>
     * Two authors are equal if they have the same id and name.
     *
     * @param o the object to compare with this author
     * @return true if this author is equal to o, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (!Objects.equals(id, author.id)) return false;
        return Objects.equals(name, author.name);
    }

    /**
     * Returns the hash code of this author.
     * The hash code is computed based on the id and name of the author.
     *
     * @return the hash code of this author
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /**
     * Returns a string representation of this author.
     * The string contains the id and name of the author in curly braces.
     *
     * @return a string representation of this author
     */
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
