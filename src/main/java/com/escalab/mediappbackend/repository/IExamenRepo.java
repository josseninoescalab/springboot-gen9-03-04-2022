package com.escalab.mediappbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.mediappbackend.model.Examen;

public interface IExamenRepo extends JpaRepository<Examen, Integer> {

}
