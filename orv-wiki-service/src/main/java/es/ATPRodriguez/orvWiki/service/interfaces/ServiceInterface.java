package es.ATPRodriguez.orvWiki.service.interfaces;

import es.ATPRodriguez.orvWiki.exception.ResourceNotFoundException;

import java.util.List;

public interface ServiceInterface<T> {
    List<T> getAll();
    T getById(int id) throws ResourceNotFoundException;
    public T create(T t);
    T update(int id, T t) throws ResourceNotFoundException;
    void delete(int id) throws ResourceNotFoundException;
}