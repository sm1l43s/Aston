package main.entity;

import java.util.Objects;

/**
 * A class that represents a book with a title, an ISBN, and an author.
 */
public class Book {
    // The unique identifier of the book
    private Long id;

    // The title of the book
    private String title;

    // The International Standard Book Number of the book
    private String isbn;

    // The author of the book
    private Author author;

    /**
     * Constructs a new Book object with the given id, title, isbn, and author.
     *
     * @param id     the unique identifier of the book
     * @param title  the title of the book
     * @param isbn   the International Standard Book Number of the book
     * @param author the author of the book
     */
    public Book(Long id, String title, String isbn, Author author) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }

    /**
     * Returns the id of the book.
     *
     * @return the id of the book
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the book.
     *
     * @param id the new id of the book
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title the new title of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the ISBN of the book.
     *
     * @return the ISBN of the book
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param isbn the new ISBN of the book
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Returns the author of the book.
     *
     * @return the author of the book
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author the new author of the book
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * Checks if this book is equal to another object.
     * <p>
     * Two books are equal if they have the same id, title, isbn, and author.
     *
     * @param o the object to compare with this book
     * @return true if this book is equal to o, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!Objects.equals(id, book.id)) return false;
        if (!Objects.equals(title, book.title)) return false;
        if (!Objects.equals(isbn, book.isbn)) return false;
        return Objects.equals(author, book.author);
    }

    /**
     * Returns the hash code of this book.
     * The hash code is computed based on the id, title, isbn, and author of the book.
     *
     * @return the hash code of this book
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    /**
     * Returns a string representation of this book.
     * The string contains the id, title, isbn, and author of the book in curly braces.
     *
     * @return a string representation of this book
     */
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author=" + author +
                '}';
    }
}
