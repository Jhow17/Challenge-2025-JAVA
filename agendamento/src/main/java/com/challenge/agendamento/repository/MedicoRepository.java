package com.challenge.agendamento.repository;

import com.challenge.agendamento.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
