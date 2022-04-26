package com.escalab.mediappbackend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.mediappbackend.model.Archivo;

public interface IArchivoRepo extends JpaRepository<Archivo, Integer> {
	
}
