package com.escalab.mediappbackend.service;

import com.escalab.mediappbackend.exception.ModeloNotFoundException;

import java.util.List;

public interface ICRUD<E> {

    List<E> findAll();

    E findById(Integer id) throws ModeloNotFoundException;

    E save(E e);

    E update(E e, Integer id);

    boolean deleteById(Integer id);
}
