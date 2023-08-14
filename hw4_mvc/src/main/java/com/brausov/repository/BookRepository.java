package com.brausov.repository;

import com.brausov.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    private final SessionFactory sessionFactory;
    private Session session;

    public BookRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Book> findAll() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Book> books = session.createQuery("SELECT book FROM Book book LEFT JOIN FETCH book.author").list();

        transaction.commit();
        session.close();
        return books;
    }

    public Book findById(Long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Book book = session.get(Book.class, id);

        transaction.commit();
        session.close();
        return book;
    }

    public boolean delete(Long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.remove(findById(id));

        transaction.commit();
        session.close();
        return findById(id) == null;
    }

    public Book create(Book entity) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);

        transaction.commit();
        session.close();
        return entity;
    }

    public Book update(Book entity) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return entity;
    }
}
