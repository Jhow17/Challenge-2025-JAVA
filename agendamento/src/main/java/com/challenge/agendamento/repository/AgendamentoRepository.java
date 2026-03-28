package com.challenge.agendamento.repository;

import com.challenge.agendamento.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    //lembrar de ser mais comprometido com os commits
    //nao sobir direto na main
    //DONE: fazer um metodo para verificar conflitos horarios medicos e pacientes
    @Query("""
        SELECT COUNT(a) > 0 FROM Agendamento a 
        WHERE a.medico.id = :medicoId 
        AND a.dataInicio < :dataFim 
        AND a.dataFim > :dataInicio 
        AND a.statusAgendamento != 'CANCELADO'
    """)
    boolean existSobreposicao(
            @Param("medicoId") Long medicoId,
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim
    );


}
