package com.brausov.repository;

import com.brausov.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorRepository {

    private SessionFactory sessionFactory;
    private Session session;

    public AuthorRepository() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }


    public List<Author> findAll() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Author> authors = session.createQuery("FROM Author").list();

        transaction.commit();
        session.close();
        return authors;
    }


    public Author findById(Long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Author author = session.get(Author.class, id);

        transaction.commit();
        session.close();
        return author;
    }


    public boolean delete(Long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.remove(findById(id));

        transaction.commit();
        session.close();
        return findById(id) == null;
    }

    public Author create(Author entity) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);

        transaction.commit();
        session.close();
        return entity;
    }

    public Author update(Author entity) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return entity;
    }
}
