package com.brausov.service;

import com.brausov.entity.Reader;
import com.brausov.exception.ResourceNotFoundException;
import com.brausov.repository.ReaderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService implements BaseService<Reader, Long> {

    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    @Override
    public List<Reader> findAll() {
        return (List<Reader>) readerRepository.findAll();
    }

    @Override
    public Reader findById(Long id) {
        Reader reader = readerRepository.findById(id);

        if (reader != null) {
            return reader;
        }
        throw new ResourceNotFoundException("Reader with id: " + id + " not found");
    }

    @Override
    public void deleteById(Long id) {
        Reader reader = readerRepository.findById(id);

        if (reader != null) {
            readerRepository.delete(id);
        }
        throw new ResourceNotFoundException("Reader with id: " + id + " not found");
    }

    @Override
    public Reader create(Reader entity) {
        return readerRepository.create(entity);
    }

    @Override
    public Reader update(Reader entity) {
        return readerRepository.update(entity);
    }
}
