package com.brausov.service;

import java.util.List;

/**
 * A generic interface for basic CRUD (create, read, update, delete) operations on entities of type E with keys of type K.
 *
 * @param <E> the type of the entity
 * @param <K> the type of the key
 */
public interface BaseService<E, K> {

    /**
     * Returns a list of all entities.
     *
     * @return a list of all entities
     */
    List<E> findAll();

    /**
     * Returns the entity with the specified key, or null if not found.
     *
     * @param id the key of the entity to find
     * @return the entity with the specified key, or null if not found
     */
    E findById(K id);

    /**
     * Deletes the entity with the specified key, if it exists.
     *
     * @param id the key of the entity to delete
     * @return true if the entity was deleted, false otherwise
     */
    boolean delete(K id);

    /**
     * Creates a new entity and returns it.
     *
     * @param entity the entity to create
     * @return the created entity
     */
    E create(E entity);

    /**
     * Updates an existing entity and returns it.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    E update(E entity);
}
