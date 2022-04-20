package com.escalab.mediappbackend.repository;

import com.escalab.mediappbackend.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {
}
