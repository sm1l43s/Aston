package main.repository;

import java.util.List;

/**
 * A generic interface for basic CRUD operations on entities of type E with keys of type K.
 *
 * @param <E> the entity type
 * @param <K> the key type
 */
public interface BaseRepository<E, K> {

    /**
     * Finds all entities in the repository.
     *
     * @return a list of entities, or an empty list if none found
     */
    List<E> findAll();

    /**
     * Finds an entity by its key in the repository.
     *
     * @param id the key of the entity to find
     * @return entity, or empty if not found
     */
    E findById(K id);

    /**
     * Deletes an entity by its key in the repository.
     *
     * @param id the key of the entity to delete
     * @return true if the entity was deleted, false otherwise
     */
    boolean delete(K id);

    /**
     * Creates a new entity in the repository.
     *
     * @param entity the entity to create
     * @return the created entity
     */
    E create(E entity);

    /**
     * Updates an existing entity in the repository.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    E update(E entity);
}
