package com.challenge.agendamento.repository;

import com.challenge.agendamento.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeadRepository extends JpaRepository<Lead, Long> {

    @Query("SELECT l FROM Lead l ORDER BY CASE WHEN l.statusLead = 'NAO_ATENDIDO' THEN 0 ELSE 1 END, l.dataRegistro DESC")
    List<Lead> findAllOrderByStatusPrioritario();
}