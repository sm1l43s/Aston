package main.service;

import java.util.List;

public interface BaseService<E,K> {
    List<E> findAll();

    E findById(K id);

    boolean delete(K id);

    E create(E entity);

    E update(E entity);
}
