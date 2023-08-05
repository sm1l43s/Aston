package main.repository;

import java.util.List;

public interface BaseRepository<E, K> {
    List<E> findAll();

    E findById(K id);

    boolean delete(K id);

    E create(E entity);

    E update(E entity);
}
