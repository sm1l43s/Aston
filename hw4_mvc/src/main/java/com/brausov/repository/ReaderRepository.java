package com.brausov.repository;

import com.brausov.entity.Reader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReaderRepository {

    private final SessionFactory sessionFactory;
    private Session session;

    public ReaderRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Reader> findAll() {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Reader> readers = session.createQuery("SELECT reader FROM Reader reader LEFT JOIN FETCH reader.books").list();

        transaction.commit();
        session.close();
        return readers;
    }

    public Reader findById(Long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT reader FROM Reader reader LEFT JOIN FETCH reader.books WHERE reader.id = :id";

        Reader reader = (Reader) session.createQuery(hql).setParameter("id", id).uniqueResult();

        transaction.commit();
        session.close();
        return reader;
    }

    public boolean delete(Long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.remove(findById(id));

        transaction.commit();
        session.close();
        return findById(id) == null;
    }

    public Reader create(Reader entity) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(entity);

        transaction.commit();
        session.close();
        return entity;
    }

    public Reader update(Reader entity) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return entity;
    }
}
