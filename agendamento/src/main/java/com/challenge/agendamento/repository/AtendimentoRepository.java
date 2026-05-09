package com.challenge.agendamento.repository;

import com.challenge.agendamento.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    List<Atendimento> findByColaboradorIdColaborador(Long idColaborador);
}
