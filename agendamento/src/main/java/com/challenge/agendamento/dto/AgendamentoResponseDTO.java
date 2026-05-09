package com.challenge.agendamento.dto;

import com.challenge.agendamento.model.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        Long id,
        String procedimento,
        String descricao,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        StatusAgendamento statusAgendamento,
        Long pacienteId,
        String pacienteNome,
        Long medicoId,
        String medicoNome,
        Long colaboradorId,
        String colaboradorNome
) {


}
