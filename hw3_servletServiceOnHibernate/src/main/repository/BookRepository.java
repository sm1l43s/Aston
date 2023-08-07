package main.repository;

import main.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class BookRepository implements BaseRepository<Book, Long> {

    private SessionFactory sessionFactory;
    private Session session;

    public BookRepository() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }


    @Override
    public List<Book> findAll() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Book> books = session.createQuery("FROM Book").list();

        transaction.commit();
        session.close();
        return books;
    }

    @Override
    public Book findById(Long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Book book = session.get(Book.class, id);

        transaction.commit();
        session.close();
        return book;
    }

    @Override
    public boolean delete(Long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.remove(findById(id));

        transaction.commit();
        session.close();
        return findById(id) == null ? true : false;
    }

    @Override
    public Book create(Book entity) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);

        transaction.commit();
        session.close();
        return entity;
    }

    @Override
    public Book update(Book entity) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return entity;
    }
}
